package com.starter.infra.db.ctrl;

import com.starter.infra.db.model.*;
import com.starter.infra.db.svc.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/infra/db")
public class DbMgmtCtrl {

    private final DbConnectionMgmtService connMgmt;
    private final SchemaMgmtService schemaMgmt;
    private final MigrationMgmtService migrationMgmt;
    private final SeedMgmtService seedMgmt;
    private final MockDataService mockData;
    private final DbOrchestrationService orchestration;

    public DbMgmtCtrl(DbConnectionMgmtService connMgmt, SchemaMgmtService schemaMgmt,
            MigrationMgmtService migrationMgmt, SeedMgmtService seedMgmt,
            MockDataService mockData, DbOrchestrationService orchestration) {
        this.connMgmt = connMgmt;
        this.schemaMgmt = schemaMgmt;
        this.migrationMgmt = migrationMgmt;
        this.seedMgmt = seedMgmt;
        this.mockData = mockData;
        this.orchestration = orchestration;
    }

    // ── Dashboard ──
    @GetMapping("/dashboard")
    public Map<String, Object> dashboard() {
        return orchestration.getDashboard();
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        return orchestration.getOverallHealth();
    }

    @GetMapping("/health/{schema}")
    public Map<String, Object> schemaHealth(@PathVariable String schema) {
        return orchestration.getDatabaseHealth(schema);
    }

    // ── Orchestration ──
    @PostMapping("/init/{schema}")
    public Map<String, Object> initDb(@PathVariable String schema) {
        return orchestration.initializeDatabase(schema);
    }

    @PostMapping("/init/all")
    public Map<String, Object> initAll() {
        return orchestration.initializeAllDatabases();
    }

    // ── Connections ──
    @GetMapping("/connections")
    public List<DbConnectionInfo> listConnections() {
        return connMgmt.getAllConnections();
    }

    @GetMapping("/connections/{name}")
    public DbConnectionInfo getConnection(@PathVariable String name) {
        return connMgmt.getConnection(name);
    }

    @PostMapping("/connections/{name}/test")
    public Map<String, Object> testConnection(@PathVariable String name) {
        return connMgmt.testConnection(name);
    }

    @PostMapping("/connections/test-all")
    public Map<String, Object> testAllConnections() {
        return connMgmt.testAllConnections();
    }

    @GetMapping("/connections/status")
    public Map<String, Object> connectionStatus() {
        return connMgmt.getConnectionStatus();
    }

    @GetMapping("/connections/{name}/databases")
    public List<String> listDatabases(@PathVariable String name) {
        return connMgmt.listDatabases(name);
    }

    @PostMapping("/connections/{name}/execute")
    public Map<String, Object> executeSql(@PathVariable String name, @RequestBody Map<String, String> body) {
        String sql = body.get("sql");
        if (sql == null)
            return Map.of("error", "sql field required");
        boolean success = connMgmt.executeRawSql(name, sql);
        return Map.of("success", success);
    }

    @PostMapping("/connections/{name}/query")
    public List<Map<String, Object>> querySql(@PathVariable String name, @RequestBody Map<String, String> body) {
        String sql = body.get("sql");
        return connMgmt.queryRaw(name, sql != null ? sql : "");
    }

    // ── Schemas ──
    @GetMapping("/schemas")
    public List<DbSchema> listSchemas() {
        return schemaMgmt.getAllSchemas();
    }

    @GetMapping("/schemas/{name}")
    public DbSchema getSchema(@PathVariable String name) {
        return schemaMgmt.getSchema(name);
    }

    @PostMapping("/schemas/{name}/create")
    public Map<String, Object> createSchema(@PathVariable String name) {
        return schemaMgmt.createSchema(name);
    }

    @PostMapping("/schemas/create-all")
    public Map<String, Object> createAllSchemas() {
        return schemaMgmt.createAllSchemas();
    }

    @GetMapping("/schemas/{name}/validate")
    public Map<String, Object> validateSchema(@PathVariable String name) {
        return schemaMgmt.validateSchema(name);
    }

    @GetMapping("/schemas/validate-all")
    public Map<String, Object> validateAllSchemas() {
        return schemaMgmt.validateAllSchemas();
    }

    @GetMapping("/schemas/{name}/ddl")
    public ResponseEntity<String> generateDdl(@PathVariable String name,
            @RequestParam(defaultValue = "0") int tableIndex) {
        var schema = schemaMgmt.getSchema(name);
        var tables = schema.getTables();
        if (tableIndex < 0 || tableIndex >= tables.size()) {
            return ResponseEntity.badRequest().body("Invalid table index: " + tableIndex);
        }
        String ddl = schemaMgmt.generateCreateTableDDL(
                schema.getDbType(), schema.getDatabaseName(), tables.get(tableIndex));
        return ResponseEntity.ok(ddl);
    }

    // ── Migrations ──
    @GetMapping("/migrations")
    public Map<String, List<MigrationInfo>> allMigrations() {
        return migrationMgmt.getAllMigrations();
    }

    @GetMapping("/migrations/{db}")
    public List<MigrationInfo> dbMigrations(@PathVariable String db) {
        return migrationMgmt.getMigrations(db);
    }

    @PostMapping("/migrations/{db}/discover")
    public List<MigrationInfo> discoverMigrations(@PathVariable String db) {
        return migrationMgmt.discoverMigrations(db);
    }

    @PostMapping("/migrations/{db}/{id}/apply")
    public MigrationInfo applyMigration(@PathVariable String db, @PathVariable String id) {
        return migrationMgmt.applyMigration(db, id);
    }

    @PostMapping("/migrations/{db}/apply-all")
    public List<MigrationInfo> applyAllMigrations(@PathVariable String db) {
        return migrationMgmt.applyAllPending(db);
    }

    @PostMapping("/migrations/{db}/{id}/rollback")
    public MigrationInfo rollbackMigration(@PathVariable String db, @PathVariable String id) {
        return migrationMgmt.rollbackMigration(db, id);
    }

    @GetMapping("/migrations/{db}/status")
    public Map<String, Object> migrationStatus(@PathVariable String db) {
        return migrationMgmt.getMigrationStatus(db);
    }

    @GetMapping("/migrations/status-all")
    public Map<String, Object> globalMigrationStatus() {
        return migrationMgmt.getGlobalMigrationStatus();
    }

    // ── Seeding ──
    @GetMapping("/seeds")
    public List<SeedConfig> listSeeds() {
        return seedMgmt.getAllSeedConfigs();
    }

    @GetMapping("/seeds/{id}")
    public SeedConfig getSeed(@PathVariable String id) {
        return seedMgmt.getSeedConfig(id);
    }

    @PostMapping("/seeds")
    public SeedConfig createSeed(@RequestBody SeedConfig config) {
        return seedMgmt.createSeedConfig(config);
    }

    @PostMapping("/seeds/{id}/execute")
    public Map<String, Object> executeSeed(@PathVariable String id) {
        return seedMgmt.executeSeed(id);
    }

    @PostMapping("/seeds/execute-all")
    public Map<String, Object> executeAllSeeds() {
        return seedMgmt.executeAllSeeds();
    }

    @PostMapping("/seeds/db/{db}/execute")
    public Map<String, Object> executeDbSeeds(@PathVariable String db) {
        return seedMgmt.executeSeedsForDatabase(db);
    }

    // ── Mock Data ──
    @GetMapping("/mock")
    public List<MockConfig> listMockConfigs() {
        return mockData.getAllMockConfigs();
    }

    @GetMapping("/mock/{id}")
    public MockConfig getMockConfig(@PathVariable String id) {
        return mockData.getMockConfig(id);
    }

    @PostMapping("/mock/{id}/generate")
    public Map<String, Object> generateMock(@PathVariable String id) {
        return mockData.generateMockData(id);
    }

    @PostMapping("/mock/generate-all")
    public Map<String, Object> generateAllMock() {
        return mockData.generateAllMockData();
    }

    @GetMapping("/mock/{id}/preview")
    public List<Map<String, Object>> previewMock(@PathVariable String id) {
        return mockData.previewMockData(id);
    }
}