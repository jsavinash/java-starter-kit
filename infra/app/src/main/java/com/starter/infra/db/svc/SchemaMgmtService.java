package com.starter.infra.db.svc;

import com.starter.infra.db.dbtype.DbType;
import com.starter.infra.db.model.DbSchema;
import com.starter.infra.db.model.DbSchema.*;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SchemaMgmtService {
    private static final Logger log = LoggerFactory.getLogger(SchemaMgmtService.class);
    private final Map<String, DbSchema> schemas = new LinkedHashMap<>();
    private final DbConnectionMgmtService connMgmt;

    public SchemaMgmtService(DbConnectionMgmtService connMgmt) {
        this.connMgmt = connMgmt;
    }

    @PostConstruct
    void init() {
        registerProductDbSchema();
        registerOrderDbSchema();
        registerUserDbSchema();
        registerInventoryDbSchema();
        registerMongoDbSchema();
        registerCassandraSchema();
        log.info("Registered {} database schemas", schemas.size());
    }

    public List<DbSchema> getAllSchemas() {
        return List.copyOf(schemas.values());
    }

    public DbSchema getSchema(String name) {
        var s = schemas.get(name);
        if (s == null)
            throw new IllegalArgumentException("Unknown schema: " + name);
        return s;
    }

    public Map<String, Object> createSchema(String schemaName) {
        var schema = getSchema(schemaName);
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("schema", schemaName);
        result.put("dbType", schema.getDbType().getDisplayName());
        result.put("database", schema.getDatabaseName());

        List<String> created = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        for (var table : schema.getTables()) {
            try {
                String ddl = generateCreateTableDDL(schema.getDbType(), schema.getDatabaseName(), table);
                String connName = getConnectionNameForSchema(schema);
                boolean success = connMgmt.executeRawSql(connName, ddl);
                if (success) {
                    created.add(table.getName());
                } else {
                    errors.add(table.getName() + ": execution failed");
                }
            } catch (Exception e) {
                errors.add(table.getName() + ": " + e.getMessage());
            }
        }

        result.put("tablesCreated", created);
        result.put("errors", errors);
        result.put("success", errors.isEmpty());
        return result;
    }

    public Map<String, Object> createAllSchemas() {
        Map<String, Object> results = new LinkedHashMap<>();
        for (String name : schemas.keySet()) {
            results.put(name, createSchema(name));
        }
        return results;
    }

    public Map<String, Object> validateSchema(String schemaName) {
        var schema = getSchema(schemaName);
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("schema", schemaName);
        result.put("dbType", schema.getDbType().getDisplayName());

        List<Map<String, Object>> tableValidations = new ArrayList<>();
        for (var table : schema.getTables()) {
            Map<String, Object> tv = new LinkedHashMap<>();
            tv.put("table", table.getName());
            tv.put("expectedColumns", table.getColumns().size());

            try {
                String connName = getConnectionNameForSchema(schema);
                var columns = connMgmt.queryRaw(connName,
                        "SELECT column_name, data_type, is_nullable FROM information_schema.columns " +
                                "WHERE table_name = '" + table.getName() + "' ORDER BY ordinal_position");
                tv.put("actualColumns", columns.size());
                tv.put("columns", columns);
                tv.put("exists", true);
            } catch (Exception e) {
                tv.put("exists", false);
                tv.put("error", e.getMessage());
            }
            tableValidations.add(tv);
        }
        result.put("tables", tableValidations);
        return result;
    }

    public Map<String, Object> validateAllSchemas() {
        Map<String, Object> results = new LinkedHashMap<>();
        for (String name : schemas.keySet()) {
            results.put(name, validateSchema(name));
        }
        return results;
    }

    public String generateCreateTableDDL(DbType dbType, String database, TableDef table) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS ").append(table.getName()).append(" (\n");

        List<String> colDefs = new ArrayList<>();
        for (var col : table.getColumns()) {
            StringBuilder colSb = new StringBuilder("  ");
            colSb.append(col.getName()).append(" ").append(col.getType());

            if (col.isAutoIncrement()) {
                if (dbType == DbType.POSTGRES)
                    colSb.append(" GENERATED ALWAYS AS IDENTITY");
            }
            if (!col.isNullable())
                colSb.append(" NOT NULL");
            if (col.getDefaultValue() != null)
                colSb.append(" DEFAULT ").append(col.getDefaultValue());

            colDefs.add(colSb.toString());
        }

        if (table.getPrimaryKeys() != null && !table.getPrimaryKeys().isEmpty()) {
            colDefs.add("  PRIMARY KEY (" + String.join(", ", table.getPrimaryKeys()) + ")");
        }

        sb.append(String.join(",\n", colDefs));
        sb.append("\n);");

        if (table.getComment() != null) {
            sb.append("\nCOMMENT ON TABLE ").append(table.getName())
                    .append(" IS '").append(table.getComment()).append("';");
        }

        return sb.toString();
    }

    private String getConnectionNameForSchema(DbSchema schema) {
        if (schema.getDbType() == DbType.POSTGRES) {
            return "postgres-master";
        } else if (schema.getDbType() == DbType.MONGODB) {
            return "mongodb";
        } else if (schema.getDbType() == DbType.CASSANDRA) {
            return "cassandra";
        }
        return "postgres-master";
    }

    private void registerProductDbSchema() {
        var tables = List.of(
                new TableDef("categories", List.of(
                        new ColumnDef("id", "BIGSERIAL"), new ColumnDef("name", "VARCHAR(100)"),
                        new ColumnDef("slug", "VARCHAR(100)"), new ColumnDef("description", "TEXT"),
                        new ColumnDef("parent_id", "BIGINT"), new ColumnDef("image_url", "VARCHAR(500)"),
                        new ColumnDef("is_active", "BOOLEAN"), new ColumnDef("sort_order", "INTEGER"),
                        new ColumnDef("created_at", "TIMESTAMP WITH TIME ZONE"),
                        new ColumnDef("updated_at", "TIMESTAMP WITH TIME ZONE"))) {
                    {
                        setPrimaryKeys(List.of("id"));
                        setComment("Product categories hierarchy");
                    }
                },
                new TableDef("products", List.of(
                        new ColumnDef("id", "BIGSERIAL"), new ColumnDef("sku", "VARCHAR(50)"),
                        new ColumnDef("name", "VARCHAR(200)"), new ColumnDef("slug", "VARCHAR(200)"),
                        new ColumnDef("description", "TEXT"), new ColumnDef("category_id", "BIGINT"),
                        new ColumnDef("brand", "VARCHAR(100)"), new ColumnDef("unit_price", "DECIMAL(10,2)"),
                        new ColumnDef("compare_price", "DECIMAL(10,2)"), new ColumnDef("cost_price", "DECIMAL(10,2)"),
                        new ColumnDef("currency", "VARCHAR(3)"), new ColumnDef("is_active", "BOOLEAN"),
                        new ColumnDef("is_featured", "BOOLEAN"), new ColumnDef("tax_class", "VARCHAR(50)"),
                        new ColumnDef("weight", "DECIMAL(10,2)"), new ColumnDef("weight_unit", "VARCHAR(10)"),
                        new ColumnDef("meta_title", "VARCHAR(200)"), new ColumnDef("meta_description", "TEXT"),
                        new ColumnDef("created_at", "TIMESTAMP WITH TIME ZONE"),
                        new ColumnDef("updated_at", "TIMESTAMP WITH TIME ZONE"))) {
                    {
                        setPrimaryKeys(List.of("id"));
                        setComment("Main product catalog");
                    }
                },
                new TableDef("product_variants", List.of(
                        new ColumnDef("id", "BIGSERIAL"), new ColumnDef("product_id", "BIGINT"),
                        new ColumnDef("sku", "VARCHAR(50)"), new ColumnDef("name", "VARCHAR(200)"),
                        new ColumnDef("options", "JSONB"), new ColumnDef("unit_price", "DECIMAL(10,2)"),
                        new ColumnDef("is_active", "BOOLEAN"), new ColumnDef("created_at", "TIMESTAMP WITH TIME ZONE"),
                        new ColumnDef("updated_at", "TIMESTAMP WITH TIME ZONE"))) {
                    {
                        setPrimaryKeys(List.of("id"));
                        setComment("Product variants (size, color, etc)");
                    }
                },
                new TableDef("product_images", List.of(
                        new ColumnDef("id", "BIGSERIAL"), new ColumnDef("product_id", "BIGINT"),
                        new ColumnDef("variant_id", "BIGINT"), new ColumnDef("url", "VARCHAR(500)"),
                        new ColumnDef("alt_text", "VARCHAR(200)"), new ColumnDef("sort_order", "INTEGER"),
                        new ColumnDef("is_primary", "BOOLEAN"),
                        new ColumnDef("created_at", "TIMESTAMP WITH TIME ZONE"))) {
                    {
                        setPrimaryKeys(List.of("id"));
                        setComment("Product images gallery");
                    }
                },
                new TableDef("product_tags", List.of(
                        new ColumnDef("id", "BIGSERIAL"), new ColumnDef("name", "VARCHAR(50)"),
                        new ColumnDef("slug", "VARCHAR(50)"),
                        new ColumnDef("created_at", "TIMESTAMP WITH TIME ZONE"))) {
                    {
                        setPrimaryKeys(List.of("id"));
                        setComment("Product tags for filtering");
                    }
                },
                new TableDef("product_tag_mapping", List.of(
                        new ColumnDef("product_id", "BIGINT"), new ColumnDef("tag_id", "BIGINT"),
                        new ColumnDef("created_at", "TIMESTAMP WITH TIME ZONE"))) {
                    {
                        setPrimaryKeys(List.of("product_id", "tag_id"));
                        setComment("Many-to-many product-tag mapping");
                    }
                });
        var schema = new DbSchema("productdb", DbType.POSTGRES, "productdb", tables);
        schema.setInitScript("database/postgres/init/001-init-productdb.sql");
        schema.setMigrationScript("database/postgres/migrations/productdb/");
        schemas.put("productdb", schema);
    }

    private void registerOrderDbSchema() {
        var tables = List.of(
                new TableDef("orders", List.of(
                        new ColumnDef("id", "BIGSERIAL"), new ColumnDef("order_number", "VARCHAR(50)"),
                        new ColumnDef("user_id", "BIGINT"), new ColumnDef("status", "VARCHAR(50)"),
                        new ColumnDef("subtotal", "DECIMAL(10,2)"), new ColumnDef("tax_amount", "DECIMAL(10,2)"),
                        new ColumnDef("shipping_amount", "DECIMAL(10,2)"),
                        new ColumnDef("discount_amount", "DECIMAL(10,2)"),
                        new ColumnDef("total_amount", "DECIMAL(10,2)"), new ColumnDef("currency", "VARCHAR(3)"),
                        new ColumnDef("shipping_address", "JSONB"), new ColumnDef("billing_address", "JSONB"),
                        new ColumnDef("payment_method", "VARCHAR(50)"), new ColumnDef("payment_status", "VARCHAR(50)"),
                        new ColumnDef("notes", "TEXT"), new ColumnDef("created_at", "TIMESTAMP WITH TIME ZONE"),
                        new ColumnDef("updated_at", "TIMESTAMP WITH TIME ZONE"))) {
                    {
                        setPrimaryKeys(List.of("id"));
                        setComment("Customer orders");
                    }
                },
                new TableDef("order_items", List.of(
                        new ColumnDef("id", "BIGSERIAL"), new ColumnDef("order_id", "BIGINT"),
                        new ColumnDef("product_id", "BIGINT"), new ColumnDef("variant_id", "BIGINT"),
                        new ColumnDef("product_name", "VARCHAR(200)"), new ColumnDef("sku", "VARCHAR(50)"),
                        new ColumnDef("quantity", "INTEGER"), new ColumnDef("unit_price", "DECIMAL(10,2)"),
                        new ColumnDef("total_price", "DECIMAL(10,2)"),
                        new ColumnDef("created_at", "TIMESTAMP WITH TIME ZONE"))) {
                    {
                        setPrimaryKeys(List.of("id"));
                        setComment("Order line items");
                    }
                },
                new TableDef("order_status_history", List.of(
                        new ColumnDef("id", "BIGSERIAL"), new ColumnDef("order_id", "BIGINT"),
                        new ColumnDef("from_status", "VARCHAR(50)"), new ColumnDef("to_status", "VARCHAR(50)"),
                        new ColumnDef("changed_by", "VARCHAR(100)"), new ColumnDef("notes", "TEXT"),
                        new ColumnDef("created_at", "TIMESTAMP WITH TIME ZONE"))) {
                    {
                        setPrimaryKeys(List.of("id"));
                        setComment("Order status change audit trail");
                    }
                },
                new TableDef("shipments", List.of(
                        new ColumnDef("id", "BIGSERIAL"), new ColumnDef("order_id", "BIGINT"),
                        new ColumnDef("tracking_number", "VARCHAR(100)"), new ColumnDef("carrier", "VARCHAR(50)"),
                        new ColumnDef("status", "VARCHAR(50)"), new ColumnDef("shipped_at", "TIMESTAMP WITH TIME ZONE"),
                        new ColumnDef("delivered_at", "TIMESTAMP WITH TIME ZONE"),
                        new ColumnDef("created_at", "TIMESTAMP WITH TIME ZONE"),
                        new ColumnDef("updated_at", "TIMESTAMP WITH TIME ZONE"))) {
                    {
                        setPrimaryKeys(List.of("id"));
                        setComment("Order shipment tracking");
                    }
                });
        var schema = new DbSchema("orderdb", DbType.POSTGRES, "orderdb", tables);
        schema.setInitScript("database/postgres/init/002-init-orderdb.sql");
        schema.setMigrationScript("database/postgres/migrations/orderdb/");
        schemas.put("orderdb", schema);
    }

    private void registerUserDbSchema() {
        var tables = List.of(
                new TableDef("users", List.of(
                        new ColumnDef("id", "BIGSERIAL"), new ColumnDef("email", "VARCHAR(255)"),
                        new ColumnDef("password_hash", "VARCHAR(255)"), new ColumnDef("first_name", "VARCHAR(100)"),
                        new ColumnDef("last_name", "VARCHAR(100)"), new ColumnDef("phone", "VARCHAR(20)"),
                        new ColumnDef("email_verified", "BOOLEAN"), new ColumnDef("is_active", "BOOLEAN"),
                        new ColumnDef("last_login_at", "TIMESTAMP WITH TIME ZONE"),
                        new ColumnDef("created_at", "TIMESTAMP WITH TIME ZONE"),
                        new ColumnDef("updated_at", "TIMESTAMP WITH TIME ZONE"))) {
                    {
                        setPrimaryKeys(List.of("id"));
                        setComment("User accounts");
                    }
                },
                new TableDef("addresses", List.of(
                        new ColumnDef("id", "BIGSERIAL"), new ColumnDef("user_id", "BIGINT"),
                        new ColumnDef("type", "VARCHAR(20)"), new ColumnDef("first_name", "VARCHAR(100)"),
                        new ColumnDef("last_name", "VARCHAR(100)"), new ColumnDef("address_line1", "VARCHAR(255)"),
                        new ColumnDef("address_line2", "VARCHAR(255)"), new ColumnDef("city", "VARCHAR(100)"),
                        new ColumnDef("state", "VARCHAR(100)"), new ColumnDef("zip_code", "VARCHAR(20)"),
                        new ColumnDef("country", "VARCHAR(100)"), new ColumnDef("phone", "VARCHAR(20)"),
                        new ColumnDef("is_default", "BOOLEAN"), new ColumnDef("created_at", "TIMESTAMP WITH TIME ZONE"),
                        new ColumnDef("updated_at", "TIMESTAMP WITH TIME ZONE"))) {
                    {
                        setPrimaryKeys(List.of("id"));
                        setComment("User addresses");
                    }
                },
                new TableDef("roles", List.of(
                        new ColumnDef("id", "BIGSERIAL"), new ColumnDef("name", "VARCHAR(50)"),
                        new ColumnDef("description", "TEXT"),
                        new ColumnDef("created_at", "TIMESTAMP WITH TIME ZONE"))) {
                    {
                        setPrimaryKeys(List.of("id"));
                        setComment("User roles");
                    }
                },
                new TableDef("user_roles", List.of(
                        new ColumnDef("user_id", "BIGINT"), new ColumnDef("role_id", "BIGINT"),
                        new ColumnDef("created_at", "TIMESTAMP WITH TIME ZONE"))) {
                    {
                        setPrimaryKeys(List.of("user_id", "role_id"));
                        setComment("Many-to-many user-role mapping");
                    }
                });
        var schema = new DbSchema("userdb", DbType.POSTGRES, "userdb", tables);
        schema.setInitScript("database/postgres/init/003-init-userdb.sql");
        schema.setMigrationScript("database/postgres/migrations/userdb/");
        schemas.put("userdb", schema);
    }

    private void registerInventoryDbSchema() {
        var tables = List.of(
                new TableDef("warehouses", List.of(
                        new ColumnDef("id", "BIGSERIAL"), new ColumnDef("name", "VARCHAR(200)"),
                        new ColumnDef("code", "VARCHAR(50)"), new ColumnDef("address", "JSONB"),
                        new ColumnDef("is_active", "BOOLEAN"), new ColumnDef("created_at", "TIMESTAMP WITH TIME ZONE"),
                        new ColumnDef("updated_at", "TIMESTAMP WITH TIME ZONE"))) {
                    {
                        setPrimaryKeys(List.of("id"));
                        setComment("Warehouse locations");
                    }
                },
                new TableDef("inventory", List.of(
                        new ColumnDef("id", "BIGSERIAL"), new ColumnDef("product_id", "BIGINT"),
                        new ColumnDef("variant_id", "BIGINT"), new ColumnDef("warehouse_id", "BIGINT"),
                        new ColumnDef("quantity_on_hand", "INTEGER"), new ColumnDef("quantity_reserved", "INTEGER"),
                        new ColumnDef("quantity_available", "INTEGER"), new ColumnDef("reorder_point", "INTEGER"),
                        new ColumnDef("reorder_quantity", "INTEGER"),
                        new ColumnDef("created_at", "TIMESTAMP WITH TIME ZONE"),
                        new ColumnDef("updated_at", "TIMESTAMP WITH TIME ZONE"))) {
                    {
                        setPrimaryKeys(List.of("id"));
                        setComment("Product inventory levels");
                    }
                },
                new TableDef("inventory_transactions", List.of(
                        new ColumnDef("id", "BIGSERIAL"), new ColumnDef("inventory_id", "BIGINT"),
                        new ColumnDef("type", "VARCHAR(50)"), new ColumnDef("quantity", "INTEGER"),
                        new ColumnDef("reference_type", "VARCHAR(50)"), new ColumnDef("reference_id", "BIGINT"),
                        new ColumnDef("notes", "TEXT"), new ColumnDef("created_by", "VARCHAR(100)"),
                        new ColumnDef("created_at", "TIMESTAMP WITH TIME ZONE"))) {
                    {
                        setPrimaryKeys(List.of("id"));
                        setComment("Inventory movement audit trail");
                    }
                },
                new TableDef("stock_alerts", List.of(
                        new ColumnDef("id", "BIGSERIAL"), new ColumnDef("product_id", "BIGINT"),
                        new ColumnDef("warehouse_id", "BIGINT"), new ColumnDef("alert_type", "VARCHAR(50)"),
                        new ColumnDef("message", "TEXT"), new ColumnDef("is_resolved", "BOOLEAN"),
                        new ColumnDef("resolved_at", "TIMESTAMP WITH TIME ZONE"),
                        new ColumnDef("created_at", "TIMESTAMP WITH TIME ZONE"))) {
                    {
                        setPrimaryKeys(List.of("id"));
                        setComment("Low stock and out-of-stock alerts");
                    }
                });
        var schema = new DbSchema("inventorydb", DbType.POSTGRES, "inventorydb", tables);
        schema.setInitScript("database/postgres/init/004-init-inventorydb.sql");
        schema.setMigrationScript("database/postgres/migrations/inventorydb/");
        schemas.put("inventorydb", schema);
    }

    private void registerMongoDbSchema() {
        var tables = List.of(
                new TableDef("products", List.of(
                        new ColumnDef("_id", "ObjectId"), new ColumnDef("name", "String"),
                        new ColumnDef("description", "String"), new ColumnDef("category", "String"),
                        new ColumnDef("price", "Number"), new ColumnDef("attributes", "Object"),
                        new ColumnDef("reviews", "Array"), new ColumnDef("createdAt", "Date"),
                        new ColumnDef("updatedAt", "Date"))) {
                    {
                        setComment("MongoDB product catalog (flexible schema)");
                    }
                },
                new TableDef("sessions", List.of(
                        new ColumnDef("_id", "ObjectId"), new ColumnDef("userId", "String"),
                        new ColumnDef("token", "String"), new ColumnDef("expiresAt", "Date"),
                        new ColumnDef("createdAt", "Date"))) {
                    {
                        setComment("User sessions");
                    }
                },
                new TableDef("audit_logs", List.of(
                        new ColumnDef("_id", "ObjectId"), new ColumnDef("action", "String"),
                        new ColumnDef("entity", "String"), new ColumnDef("entityId", "String"),
                        new ColumnDef("changes", "Object"), new ColumnDef("performedBy", "String"),
                        new ColumnDef("timestamp", "Date"))) {
                    {
                        setComment("Audit log entries");
                    }
                });
        var schema = new DbSchema("ecommerce", DbType.MONGODB, "ecommerce", tables);
        schema.setInitScript("database/mongodb/init/001-init-ecommerce.js");
        schemas.put("ecommerce-mongo", schema);
    }

    private void registerCassandraSchema() {
        var tables = List.of(
                new TableDef("user_sessions", List.of(
                        new ColumnDef("user_id", "UUID"), new ColumnDef("session_id", "UUID"),
                        new ColumnDef("login_time", "TIMESTAMP"), new ColumnDef("ip_address", "INET"),
                        new ColumnDef("user_agent", "TEXT"), new ColumnDef("is_active", "BOOLEAN"))) {
                    {
                        setPrimaryKeys(List.of("user_id", "session_id"));
                        setComment("User session data for high-write scenarios");
                    }
                },
                new TableDef("event_log", List.of(
                        new ColumnDef("event_type", "TEXT"), new ColumnDef("event_time", "TIMESTAMP"),
                        new ColumnDef("event_id", "UUID"), new ColumnDef("user_id", "UUID"),
                        new ColumnDef("payload", "TEXT"), new ColumnDef("source", "TEXT"))) {
                    {
                        setPrimaryKeys(List.of("event_type", "event_time", "event_id"));
                        setComment("Time-series event log");
                    }
                },
                new TableDef("product_recommendations", List.of(
                        new ColumnDef("product_id", "UUID"), new ColumnDef("recommended_product_id", "UUID"),
                        new ColumnDef("score", "FLOAT"), new ColumnDef("category", "TEXT"),
                        new ColumnDef("updated_at", "TIMESTAMP"))) {
                    {
                        setPrimaryKeys(List.of("product_id", "recommended_product_id"));
                        setComment("Product recommendation pairs");
                    }
                });
        var schema = new DbSchema("ecommerce", DbType.CASSANDRA, "ecommerce", tables);
        schema.setInitScript("database/cassandra/init/001-init-ecommerce.cql");
        schemas.put("ecommerce-cassandra", schema);
    }
}