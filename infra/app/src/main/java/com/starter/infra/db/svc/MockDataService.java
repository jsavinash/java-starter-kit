package com.starter.infra.db.svc;

import com.starter.infra.db.model.MockConfig;
import com.starter.infra.db.model.MockConfig.FieldMockDef;
import com.starter.infra.db.model.MockConfig.MockDataType;
import com.starter.infra.db.model.MockConfig.MockStatus;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class MockDataService {
    private static final Logger log = LoggerFactory.getLogger(MockDataService.class);
    private final Map<String, MockConfig> mockConfigs = new LinkedHashMap<>();
    private final DbConnectionMgmtService connMgmt;
    private final String dbAssetsPath;

    private static final String[] FIRST_NAMES = { "John", "Jane", "Alice", "Bob", "Charlie", "Diana", "Eve",
            "Frank", "Grace", "Henry", "Ivy", "Jack", "Kate", "Leo", "Mia", "Noah", "Olivia", "Paul" };
    private static final String[] LAST_NAMES = { "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia",
            "Miller", "Davis", "Rodriguez", "Martinez", "Anderson", "Taylor", "Thomas", "Jackson" };
    private static final String[] CITIES = { "New York", "Los Angeles", "Chicago", "Houston", "Phoenix",
            "Philadelphia", "San Antonio", "San Diego", "Dallas", "Austin" };
    private static final String[] STATES = { "CA", "NY", "TX", "FL", "IL", "PA", "OH", "GA", "NC", "MI" };
    private static final String[] STREETS = { "Main St", "Oak Ave", "Elm St", "Park Blvd", "Broadway",
            "Maple Dr", "Cedar Ln", "Pine St", "Lake Ave", "Hill Rd" };
    private static final String[] COMPANIES = { "TechCorp", "DataFlow", "CloudNine", "ByteForge",
            "Quantum Labs", "NexGen", "SmartSys", "InfoWorld", "Cyberdyne", "OmniTech" };
    private static final String[] DOMAINS = { "gmail.com", "yahoo.com", "outlook.com", "company.com",
            "example.org", "mail.net", "test.io" };
    private static final String[] JOB_TITLES = { "Engineer", "Manager", "Director", "Analyst",
            "Consultant", "Developer", "Architect", "Coordinator", "Specialist", "Lead" };
    private static final String[] PRODUCTS = { "Widget", "Gadget", "Doohickey", "Thingamajig",
            "Whatchamacallit", "Contraption", "Device", "Apparatus", "Instrument", "Tool" };

    public MockDataService(DbConnectionMgmtService connMgmt, String dbAssetsPath) {
        this.connMgmt = connMgmt;
        this.dbAssetsPath = dbAssetsPath;
    }

    @PostConstruct
    void init() {
        registerMockConfig("mock-products", "Mock Products", "productdb", "products", 50);
        registerMockConfig("mock-users", "Mock Users", "userdb", "users", 25);
        registerMockConfig("mock-orders", "Mock Orders", "orderdb", "orders", 100);
        registerMockConfig("mock-categories", "Mock Categories", "productdb", "categories", 10);
        registerMockConfig("mock-inventory", "Mock Inventory", "inventorydb", "inventory", 200);
        log.info("Registered {} mock data configurations", mockConfigs.size());
    }

    public List<MockConfig> getAllMockConfigs() {
        return List.copyOf(mockConfigs.values());
    }

    public MockConfig getMockConfig(String id) {
        var cfg = mockConfigs.get(id);
        if (cfg == null)
            throw new IllegalArgumentException("Unknown mock config: " + id);
        return cfg;
    }

    public Map<String, Object> generateMockData(String mockId) {
        var config = getMockConfig(mockId);
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("mockId", mockId);
        result.put("table", config.getTableName());
        result.put("recordCount", config.getRecordCount());

        config.setStatus(MockStatus.RUNNING);

        try {
            List<Map<String, Object>> records = new ArrayList<>();
            for (int i = 0; i < config.getRecordCount(); i++) {
                records.add(generateRecord(i, config));
            }

            String insertSql = buildInsertSql(config.getTableName(), records);
            String connName = getConnectionName(config.getDatabaseName());
            boolean success = connMgmt.executeRawSql(connName,
                    "SET search_path TO " + config.getDatabaseName() + ";\n" + insertSql);

            if (success) {
                config.setStatus(MockStatus.SUCCESS);
                config.setRecordsGenerated(records.size());
                result.put("success", true);
                result.put("recordsGenerated", records.size());
                result.put("preview", records.size() > 3 ? records.subList(0, 3) : records);
                log.info("Mock data generated: {} ({} records)", mockId, records.size());
            } else {
                config.setStatus(MockStatus.FAILED);
                config.setErrorMessage("Insert execution failed");
                result.put("success", false);
                result.put("error", "Mock data insertion failed");
            }
        } catch (Exception e) {
            config.setStatus(MockStatus.FAILED);
            config.setErrorMessage(e.getMessage());
            result.put("success", false);
            result.put("error", e.getMessage());
            log.error("Mock data generation failed: {} - {}", mockId, e.getMessage());
        }

        return result;
    }

    public Map<String, Object> generateAllMockData() {
        Map<String, Object> results = new LinkedHashMap<>();
        for (var cfg : mockConfigs.values()) {
            results.put(cfg.getId(), generateMockData(cfg.getId()));
        }
        return results;
    }

    public List<Map<String, Object>> previewMockData(String mockId) {
        var config = getMockConfig(mockId);
        List<Map<String, Object>> preview = new ArrayList<>();
        for (int i = 0; i < Math.min(5, config.getRecordCount()); i++) {
            preview.add(generateRecord(i, config));
        }
        return preview;
    }

    private Map<String, Object> generateRecord(int index, MockConfig config) {
        Map<String, Object> record = new LinkedHashMap<>();

        if (config.getFieldDefinitions() != null && !config.getFieldDefinitions().isEmpty()) {
            for (var entry : config.getFieldDefinitions().entrySet()) {
                record.put(entry.getKey(), generateFieldValue(entry.getValue(), index));
            }
        } else {
            record.put("id", index + 1);
            record.put("name", getRandom(PRODUCTS) + " " + (index + 1));
            record.put("description", "Auto-generated mock record #" + (index + 1));
            record.put("is_active", true);
            record.put("created_at", LocalDateTime.now().minusDays(ThreadLocalRandom.current().nextInt(365))
                    .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            record.put("updated_at", LocalDateTime.now()
                    .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }

        return record;
    }

    private Object generateFieldValue(FieldMockDef def, int index) {
        if (def == null)
            return null;

        switch (def.getDataType()) {
            case FIRST_NAME:
                return getRandom(FIRST_NAMES);
            case LAST_NAME:
                return getRandom(LAST_NAMES);
            case FULL_NAME:
                return getRandom(FIRST_NAMES) + " " + getRandom(LAST_NAMES);
            case EMAIL:
                return (getRandom(FIRST_NAMES) + "." + getRandom(LAST_NAMES) + "@" + getRandom(DOMAINS)).toLowerCase();
            case PHONE:
                return String.format("+1-%03d-%03d-%04d",
                        ThreadLocalRandom.current().nextInt(200, 999),
                        ThreadLocalRandom.current().nextInt(100, 999),
                        ThreadLocalRandom.current().nextInt(1000, 9999));
            case ADDRESS:
                return ThreadLocalRandom.current().nextInt(100, 9999) + " " + getRandom(STREETS);
            case CITY:
                return getRandom(CITIES);
            case STATE:
                return getRandom(STATES);
            case ZIP_CODE:
                return String.format("%05d", ThreadLocalRandom.current().nextInt(10000, 99999));
            case UUID:
                return UUID.randomUUID().toString();
            case COMPANY:
                return getRandom(COMPANIES);
            case JOB_TITLE:
                return getRandom(JOB_TITLES);
            case RANDOM_INT:
                return ThreadLocalRandom.current().nextInt(
                        def.getMinValue() != null ? ((Number) def.getMinValue()).intValue() : 1,
                        def.getMaxValue() != null ? ((Number) def.getMaxValue()).intValue() : 1000);
            case RANDOM_LONG:
                return ThreadLocalRandom.current().nextLong(
                        def.getMinValue() != null ? ((Number) def.getMinValue()).longValue() : 1L,
                        def.getMaxValue() != null ? ((Number) def.getMaxValue()).longValue() : 100000L);
            case RANDOM_DOUBLE:
                return ThreadLocalRandom.current().nextDouble(
                        def.getMinValue() != null ? ((Number) def.getMinValue()).doubleValue() : 0.0,
                        def.getMaxValue() != null ? ((Number) def.getMaxValue()).doubleValue() : 1000.0);
            case CURRENCY_AMOUNT:
                return Math.round(ThreadLocalRandom.current().nextDouble(1.0, 9999.99) * 100.0) / 100.0;
            case BOOLEAN:
                return ThreadLocalRandom.current().nextBoolean();
            case PAST_DATE:
                return LocalDate.now().minusDays(ThreadLocalRandom.current().nextInt(1, 365))
                        .format(DateTimeFormatter.ISO_LOCAL_DATE);
            case FUTURE_DATE:
                return LocalDate.now().plusDays(ThreadLocalRandom.current().nextInt(1, 365))
                        .format(DateTimeFormatter.ISO_LOCAL_DATE);
            case DATE_TIME:
                return LocalDateTime.now().minusDays(ThreadLocalRandom.current().nextInt(365))
                        .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            case TIMESTAMP:
                return LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
            case COLOR:
                return String.format("#%02x%02x%02x",
                        ThreadLocalRandom.current().nextInt(256),
                        ThreadLocalRandom.current().nextInt(256),
                        ThreadLocalRandom.current().nextInt(256));
            case URL:
                return "https://www." + getRandom(COMPANIES).toLowerCase() + ".com";
            case IP_ADDRESS:
                return ThreadLocalRandom.current().nextInt(10, 223) + "." +
                        ThreadLocalRandom.current().nextInt(0, 255) + "." +
                        ThreadLocalRandom.current().nextInt(0, 255) + "." +
                        ThreadLocalRandom.current().nextInt(1, 254);
            default:
                return "mock-value-" + index;
        }
    }

    private String buildInsertSql(String tableName, List<Map<String, Object>> records) {
        if (records.isEmpty())
            return "";

        Set<String> columns = records.get(0).keySet();
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(tableName)
                .append(" (").append(String.join(", ", columns)).append(") VALUES\n");

        List<String> valueRows = new ArrayList<>();
        for (var record : records) {
            List<String> values = new ArrayList<>();
            for (String col : columns) {
                Object val = record.get(col);
                if (val == null) {
                    values.add("NULL");
                } else if (val instanceof Number || val instanceof Boolean) {
                    values.add(val.toString());
                } else {
                    values.add("'" + val.toString().replace("'", "''") + "'");
                }
            }
            valueRows.add("(" + String.join(", ", values) + ")");
        }
        sb.append(String.join(",\n", valueRows)).append(";");
        return sb.toString();
    }

    private String getConnectionName(String databaseName) {
        if (databaseName.contains("mongo"))
            return "mongodb";
        if (databaseName.contains("cassandra"))
            return "cassandra";
        return "postgres-master";
    }

    private <T> T getRandom(T[] array) {
        return array[ThreadLocalRandom.current().nextInt(array.length)];
    }

    private void registerMockConfig(String id, String name, String db, String table, int count) {
        var config = new MockConfig(id, com.starter.infra.db.dbtype.DbType.POSTGRES, db, name, table, count);
        mockConfigs.put(id, config);
    }
}