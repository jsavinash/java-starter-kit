package com.starter.infra.db.svc;

import com.starter.infra.db.dbtype.DbType;
import com.starter.infra.db.model.SeedConfig;
import com.starter.infra.db.model.SeedConfig.SeedSourceType;
import com.starter.infra.db.model.SeedConfig.SeedStatus;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SeedMgmtService {
    private static final Logger log = LoggerFactory.getLogger(SeedMgmtService.class);
    private final Map<String, List<SeedConfig>> seedJobs = new ConcurrentHashMap<>();
    private final Map<String, SeedConfig> seedConfigs = new LinkedHashMap<>();
    private final DbConnectionMgmtService connMgmt;
    private final String dbAssetsPath;

    public SeedMgmtService(DbConnectionMgmtService connMgmt, String dbAssetsPath) {
        this.connMgmt = connMgmt;
        this.dbAssetsPath = dbAssetsPath;
    }

    @PostConstruct
    void init() {
        registerSeedConfig("product-categories", "Product Categories",
                DbType.POSTGRES, "productdb", SeedSourceType.SQL,
                "postgres/seed/001-product-categories.sql");
        registerSeedConfig("product-sample", "Sample Products",
                DbType.POSTGRES, "productdb", SeedSourceType.SQL,
                "postgres/seed/002-sample-products.sql");
        registerSeedConfig("users-sample", "Sample Users",
                DbType.POSTGRES, "userdb", SeedSourceType.SQL,
                "postgres/seed/003-sample-users.sql");
        registerSeedConfig("orders-sample", "Sample Orders",
                DbType.POSTGRES, "orderdb", SeedSourceType.SQL,
                "postgres/seed/004-sample-orders.sql");
        registerSeedConfig("inventory-sample", "Sample Inventory",
                DbType.POSTGRES, "inventorydb", SeedSourceType.SQL,
                "postgres/seed/005-sample-inventory.sql");
        log.info("Registered {} seed configurations", seedConfigs.size());
    }

    public List<SeedConfig> getAllSeedConfigs() {
        return List.copyOf(seedConfigs.values());
    }

    public SeedConfig getSeedConfig(String id) {
        var cfg = seedConfigs.get(id);
        if (cfg == null)
            throw new IllegalArgumentException("Unknown seed config: " + id);
        return cfg;
    }

    public Map<String, Object> executeSeed(String seedId) {
        var config = getSeedConfig(seedId);
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("seedId", seedId);
        result.put("name", config.getName());
        result.put("database", config.getDatabaseName());
        result.put("sourceType", config.getSourceType());

        config.setStatus(SeedStatus.RUNNING);

        try {
            Path seedPath = Path.of(dbAssetsPath, config.getSourcePath());
            if (!seedPath.toFile().exists()) {
                config.setStatus(SeedStatus.FAILED);
                config.setErrorMessage("Seed file not found: " + seedPath);
                result.put("success", false);
                result.put("error", "Seed file not found");
                return result;
            }

            String sql = Files.readString(seedPath);
            String connName = getConnectionName(config.getDbType(), config.getDatabaseName());

            if (config.isTruncateBefore()) {
                connMgmt.executeRawSql(connName, extractTruncateStatements(sql));
            }

            boolean success = connMgmt.executeRawSql(connName,
                    "SET search_path TO " + config.getDatabaseName() + ";\n" + sql);

            if (success) {
                config.setStatus(SeedStatus.SUCCESS);
                config.setRecordsInserted(countInserts(sql));
                result.put("success", true);
                result.put("recordsInserted", config.getRecordsInserted());
                log.info("Seed executed: {} ({} records)", seedId, config.getRecordsInserted());
            } else {
                config.setStatus(SeedStatus.FAILED);
                config.setErrorMessage("Seed SQL execution failed");
                result.put("success", false);
                result.put("error", "Seed execution failed");
            }
        } catch (Exception e) {
            config.setStatus(SeedStatus.FAILED);
            config.setErrorMessage(e.getMessage());
            result.put("success", false);
            result.put("error", e.getMessage());
            log.error("Seed failed: {} - {}", seedId, e.getMessage());
        }

        return result;
    }

    public Map<String, Object> executeAllSeeds() {
        Map<String, Object> results = new LinkedHashMap<>();
        for (var cfg : seedConfigs.values()) {
            results.put(cfg.getId(), executeSeed(cfg.getId()));
        }
        return results;
    }

    public Map<String, Object> executeSeedsForDatabase(String databaseName) {
        Map<String, Object> results = new LinkedHashMap<>();
        for (var cfg : seedConfigs.values()) {
            if (cfg.getDatabaseName().equals(databaseName)) {
                results.put(cfg.getId(), executeSeed(cfg.getId()));
            }
        }
        return results;
    }

    public SeedConfig createSeedConfig(SeedConfig config) {
        config.setId(UUID.randomUUID().toString());
        config.setStatus(SeedStatus.PENDING);
        seedConfigs.put(config.getId(), config);
        return config;
    }

    private void registerSeedConfig(String id, String name, DbType dbType, String database,
            SeedSourceType sourceType, String path) {
        var config = new SeedConfig(id, dbType, database, name, sourceType, path);
        seedConfigs.put(id, config);
    }

    private String getConnectionName(DbType dbType, String databaseName) {
        if (dbType == DbType.POSTGRES)
            return "postgres-master";
        if (dbType == DbType.MONGODB)
            return "mongodb";
        if (dbType == DbType.CASSANDRA)
            return "cassandra";
        return "postgres-master";
    }

    private String extractTruncateStatements(String sql) {
        StringBuilder truncates = new StringBuilder();
        String[] lines = sql.split("\n");
        for (String line : lines) {
            String trimmed = line.trim().toUpperCase();
            if (trimmed.startsWith("INSERT INTO")) {
                String tableName = trimmed.replace("INSERT INTO", "").trim().split("\\s")[0];
                truncates.append("TRUNCATE TABLE ").append(tableName).append(" CASCADE;\n");
            }
        }
        return truncates.toString();
    }

    private int countInserts(String sql) {
        int count = 0;
        String[] lines = sql.split("\n");
        for (String line : lines) {
            if (line.trim().toUpperCase().startsWith("INSERT INTO")) {
                count++;
            }
        }
        return count;
    }
}