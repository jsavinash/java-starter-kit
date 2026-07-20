package com.starter.infra.db.svc;

import com.starter.infra.db.dbtype.DbType;
import com.starter.infra.db.model.MigrationInfo;
import com.starter.infra.db.model.MigrationInfo.MigrationStatus;
import com.starter.infra.db.model.MigrationInfo.MigrationType;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class MigrationMgmtService {
    private static final Logger log = LoggerFactory.getLogger(MigrationMgmtService.class);
    private final Map<String, List<MigrationInfo>> migrations = new ConcurrentHashMap<>();
    private final DbConnectionMgmtService connMgmt;
    private final String dbAssetsPath;

    public MigrationMgmtService(DbConnectionMgmtService connMgmt, String dbAssetsPath) {
        this.connMgmt = connMgmt;
        this.dbAssetsPath = dbAssetsPath;
    }

    @PostConstruct
    void init() {
        log.info("MigrationMgmtService initialized. Asset path: {}", dbAssetsPath);
    }

    public List<MigrationInfo> getMigrations(String databaseName) {
        return migrations.getOrDefault(databaseName, List.of());
    }

    public Map<String, List<MigrationInfo>> getAllMigrations() {
        return Map.copyOf(migrations);
    }

    public List<MigrationInfo> discoverMigrations(String databaseName) {
        List<MigrationInfo> discovered = new ArrayList<>();
        Path migrationDir = Path.of(dbAssetsPath, "postgres", "migrations", databaseName);
        File dir = migrationDir.toFile();

        if (!dir.exists() || !dir.isDirectory()) {
            log.warn("Migration directory not found: {}", migrationDir);
            return discovered;
        }

        File[] files = dir.listFiles((d, name) -> name.endsWith(".sql"));
        if (files == null)
            return discovered;

        Arrays.sort(files);

        for (File file : files) {
            String fileName = file.getName();
            String version = extractVersion(fileName);
            String description = extractDescription(fileName);
            MigrationType type = fileName.contains("undo") ? MigrationType.UNDO
                    : fileName.contains("repeatable") ? MigrationType.REPEATABLE : MigrationType.SCHEMA;

            var info = new MigrationInfo(
                    UUID.randomUUID().toString(), DbType.POSTGRES, databaseName,
                    version, description, fileName, type);

            try {
                byte[] content = Files.readAllBytes(file.toPath());
                info.setChecksum(computeChecksum(content));
            } catch (Exception e) {
                log.warn("Failed to read migration file: {}", fileName);
            }

            discovered.add(info);
        }

        migrations.put(databaseName, discovered);
        return discovered;
    }

    public MigrationInfo applyMigration(String databaseName, String migrationId) {
        var dbMigrations = migrations.get(databaseName);
        if (dbMigrations == null)
            throw new IllegalArgumentException("No migrations found for: " + databaseName);

        var migration = dbMigrations.stream()
                .filter(m -> m.getId().equals(migrationId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Migration not found: " + migrationId));

        if (migration.getStatus() == MigrationStatus.SUCCESS) {
            throw new IllegalStateException("Migration already applied: " + migrationId);
        }

        migration.setStatus(MigrationStatus.RUNNING);
        long startTime = System.currentTimeMillis();

        try {
            Path migrationPath = Path.of(dbAssetsPath, "postgres", "migrations", databaseName, migration.getScript());
            String sql = Files.readString(migrationPath);

            boolean success = connMgmt.executeRawSql("postgres-master",
                    "SET search_path TO " + databaseName + ";\n" + sql);

            if (success) {
                migration.setStatus(MigrationStatus.SUCCESS);
                migration.setExecutedAt(LocalDateTime.now());
                migration.setExecutedBy("infra-service");
                migration.setExecutionTimeMs((int) (System.currentTimeMillis() - startTime));
                log.info("Migration applied: {} ({} ms)", migration.getScript(), migration.getExecutionTimeMs());
            } else {
                migration.setStatus(MigrationStatus.FAILED);
                migration.setErrorMessage("SQL execution returned false");
                log.error("Migration failed: {}", migration.getScript());
            }
        } catch (Exception e) {
            migration.setStatus(MigrationStatus.FAILED);
            migration.setErrorMessage(e.getMessage());
            log.error("Migration failed: {} - {}", migration.getScript(), e.getMessage());
        }

        return migration;
    }

    public List<MigrationInfo> applyAllPending(String databaseName) {
        List<MigrationInfo> applied = new ArrayList<>();
        List<MigrationInfo> dbMigrations = migrations.getOrDefault(databaseName, Collections.emptyList());

        for (MigrationInfo migration : dbMigrations) {
            if (migration.getStatus() == MigrationStatus.PENDING) {
                applied.add(applyMigration(databaseName, migration.getId()));
            }
        }
        return applied;
    }

    public MigrationInfo rollbackMigration(String databaseName, String migrationId) {
        var dbMigrations = migrations.get(databaseName);
        if (dbMigrations == null)
            throw new IllegalArgumentException("No migrations found for: " + databaseName);

        var migration = dbMigrations.stream()
                .filter(m -> m.getId().equals(migrationId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Migration not found: " + migrationId));

        if (migration.getStatus() != MigrationStatus.SUCCESS) {
            throw new IllegalStateException("Migration is not in SUCCESS state: " + migrationId);
        }

        String undoScript = migration.getScript().replace(".sql", ".undo.sql");
        Path undoPath = Path.of(dbAssetsPath, "postgres", "migrations", databaseName, undoScript);

        if (!undoPath.toFile().exists()) {
            log.warn("Undo script not found: {}", undoScript);
            migration.setStatus(MigrationStatus.UNDONE);
            migration.setErrorMessage("No undo script available, marking as undone");
            return migration;
        }

        migration.setStatus(MigrationStatus.RUNNING);
        long startTime = System.currentTimeMillis();

        try {
            String sql = Files.readString(undoPath);
            boolean success = connMgmt.executeRawSql("postgres-master",
                    "SET search_path TO " + databaseName + ";\n" + sql);

            if (success) {
                migration.setStatus(MigrationStatus.UNDONE);
                migration.setExecutionTimeMs((int) (System.currentTimeMillis() - startTime));
                log.info("Migration rolled back: {}", migration.getScript());
            } else {
                migration.setStatus(MigrationStatus.FAILED);
                migration.setErrorMessage("Rollback SQL execution failed");
            }
        } catch (Exception e) {
            migration.setStatus(MigrationStatus.FAILED);
            migration.setErrorMessage(e.getMessage());
            log.error("Rollback failed: {} - {}", migration.getScript(), e.getMessage());
        }

        return migration;
    }

    public Map<String, Object> getMigrationStatus(String databaseName) {
        var dbMigrations = migrations.getOrDefault(databaseName, List.of());
        Map<String, Object> status = new LinkedHashMap<>();
        status.put("database", databaseName);
        status.put("total", dbMigrations.size());
        status.put("applied", dbMigrations.stream().filter(m -> m.getStatus() == MigrationStatus.SUCCESS).count());
        status.put("pending", dbMigrations.stream().filter(m -> m.getStatus() == MigrationStatus.PENDING).count());
        status.put("failed", dbMigrations.stream().filter(m -> m.getStatus() == MigrationStatus.FAILED).count());
        status.put("migrations", dbMigrations);
        return status;
    }

    public Map<String, Object> getGlobalMigrationStatus() {
        Map<String, Object> global = new LinkedHashMap<>();
        int total = 0, applied = 0, pending = 0, failed = 0;
        for (var entry : migrations.entrySet()) {
            var dbMigrations = entry.getValue();
            total += dbMigrations.size();
            applied += dbMigrations.stream().filter(m -> m.getStatus() == MigrationStatus.SUCCESS).count();
            pending += dbMigrations.stream().filter(m -> m.getStatus() == MigrationStatus.PENDING).count();
            failed += dbMigrations.stream().filter(m -> m.getStatus() == MigrationStatus.FAILED).count();
        }
        global.put("total", total);
        global.put("applied", applied);
        global.put("pending", pending);
        global.put("failed", failed);
        global.put("databases", migrations.keySet());
        return global;
    }

    private String extractVersion(String fileName) {
        if (fileName.matches("V\\d+.*")) {
            return fileName.replaceAll("V(\\d+)__.*", "$1");
        }
        return "0";
    }

    private String extractDescription(String fileName) {
        if (fileName.contains("__")) {
            return fileName.substring(fileName.indexOf("__") + 2).replace(".sql", "").replace("_", " ").toLowerCase();
        }
        return fileName.replace(".sql", "").replace("_", " ");
    }

    private String computeChecksum(byte[] content) {
        try {
            var digest = MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest(content);
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception e) {
            return "unknown";
        }
    }
}