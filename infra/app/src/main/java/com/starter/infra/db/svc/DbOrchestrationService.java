package com.starter.infra.db.svc;

import com.starter.infra.db.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DbOrchestrationService {
    private static final Logger log = LoggerFactory.getLogger(DbOrchestrationService.class);

    private final DbConnectionMgmtService connMgmt;
    private final SchemaMgmtService schemaMgmt;
    private final MigrationMgmtService migrationMgmt;
    private final SeedMgmtService seedMgmt;
    private final MockDataService mockData;

    public DbOrchestrationService(DbConnectionMgmtService connMgmt, SchemaMgmtService schemaMgmt,
            MigrationMgmtService migrationMgmt, SeedMgmtService seedMgmt,
            MockDataService mockData) {
        this.connMgmt = connMgmt;
        this.schemaMgmt = schemaMgmt;
        this.migrationMgmt = migrationMgmt;
        this.seedMgmt = seedMgmt;
        this.mockData = mockData;
    }

    public Map<String, Object> getDashboard() {
        Map<String, Object> dashboard = new LinkedHashMap<>();

        var connStatus = connMgmt.getConnectionStatus();
        dashboard.put("connections", connStatus);

        var schemas = schemaMgmt.getAllSchemas();
        dashboard.put("schemas", schemas.size());

        var migrationStatus = migrationMgmt.getGlobalMigrationStatus();
        dashboard.put("migrations", migrationStatus);

        var seeds = seedMgmt.getAllSeedConfigs();
        long seedSuccess = seeds.stream().filter(s -> s.getStatus() == SeedConfig.SeedStatus.SUCCESS).count();
        dashboard.put("seeds", Map.of("total", seeds.size(), "success", seedSuccess));

        var mocks = mockData.getAllMockConfigs();
        long mockSuccess = mocks.stream().filter(m -> m.getStatus() == MockConfig.MockStatus.SUCCESS).count();
        dashboard.put("mockData", Map.of("total", mocks.size(), "success", mockSuccess));

        return dashboard;
    }

    public Map<String, Object> initializeDatabase(String schemaName) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("schema", schemaName);
        result.put("startedAt", System.currentTimeMillis());

        // Step 1: Create schema/tables
        var schemaResult = schemaMgmt.createSchema(schemaName);
        result.put("schemaCreation", schemaResult);

        if (!schemaResult.containsKey("success") || Boolean.TRUE.equals(schemaResult.get("success"))) {
            // Step 2: Discover & apply migrations
            try {
                var migrations = migrationMgmt.discoverMigrations(schemaName);
                result.put("migrationsDiscovered", migrations.size());
                if (!migrations.isEmpty()) {
                    var applied = migrationMgmt.applyAllPending(schemaName);
                    result.put("migrationsApplied", applied.size());
                }
            } catch (Exception e) {
                result.put("migrationError", e.getMessage());
            }

            // Step 3: Execute seed data
            try {
                var seedResults = seedMgmt.executeSeedsForDatabase(schemaName);
                result.put("seedResults", seedResults);
            } catch (Exception e) {
                result.put("seedError", e.getMessage());
            }

            // Step 4: Generate mock data
            try {
                var mockResults = mockData.generateAllMockData();
                result.put("mockResults", mockResults);
            } catch (Exception e) {
                result.put("mockError", e.getMessage());
            }
        }

        result.put("completedAt", System.currentTimeMillis());
        result.put("status", "completed");
        return result;
    }

    public Map<String, Object> initializeAllDatabases() {
        Map<String, Object> results = new LinkedHashMap<>();
        for (var schema : schemaMgmt.getAllSchemas()) {
            results.put(schema.getName(), initializeDatabase(schema.getName()));
        }
        return results;
    }

    public Map<String, Object> getDatabaseHealth(String schemaName) {
        Map<String, Object> health = new LinkedHashMap<>();
        health.put("schema", schemaName);

        try {
            var schema = schemaMgmt.getSchema(schemaName);
            health.put("dbType", schema.getDbType().getDisplayName());

            var validation = schemaMgmt.validateSchema(schemaName);
            health.put("validation", validation);

            var migrationStatus = migrationMgmt.getMigrationStatus(schemaName);
            health.put("migrations", migrationStatus);

            var connTest = connMgmt.testConnection(getConnectionName(schema));
            health.put("connection", connTest);

        } catch (Exception e) {
            health.put("error", e.getMessage());
        }

        return health;
    }

    public Map<String, Object> getOverallHealth() {
        Map<String, Object> health = new LinkedHashMap<>();
        health.put("status", "checking");
        health.put("timestamp", System.currentTimeMillis());

        var connResults = connMgmt.testAllConnections();
        long healthyConns = connResults.values().stream()
                .filter(r -> r instanceof Map)
                .map(r -> (Map<String, Object>) r)
                .filter(r -> Boolean.TRUE.equals(r.get("connected")))
                .count();
        health.put("connections", Map.of("total", connResults.size(), "healthy", healthyConns));
        health.put("connectionDetails", connResults);

        var schemas = schemaMgmt.getAllSchemas();
        health.put("schemas", schemas.size());

        var migrationStatus = migrationMgmt.getGlobalMigrationStatus();
        health.put("migrations", migrationStatus);

        return health;
    }

    private String getConnectionName(DbSchema schema) {
        if (schema.getDbType() == com.starter.infra.db.dbtype.DbType.POSTGRES)
            return "postgres-master";
        if (schema.getDbType() == com.starter.infra.db.dbtype.DbType.MONGODB)
            return "mongodb";
        if (schema.getDbType() == com.starter.infra.db.dbtype.DbType.CASSANDRA)
            return "cassandra";
        return "postgres-master";
    }
}