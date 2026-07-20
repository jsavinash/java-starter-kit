package com.starter.infra.db.model;

import com.starter.infra.db.dbtype.DbType;

import java.util.List;
import java.util.Map;

public class MockConfig {
    private String id;
    private DbType dbType;
    private String databaseName;
    private String name;
    private String description;
    private String tableName;
    private int recordCount;
    private MockStrategy strategy;
    private Map<String, FieldMockDef> fieldDefinitions;
    private MockStatus status;
    private int recordsGenerated;
    private String errorMessage;

    public MockConfig() {
    }

    public MockConfig(String id, DbType dbType, String databaseName, String name, String tableName, int recordCount) {
        this.id = id;
        this.dbType = dbType;
        this.databaseName = databaseName;
        this.name = name;
        this.tableName = tableName;
        this.recordCount = recordCount;
        this.strategy = MockStrategy.RANDOM;
        this.status = MockStatus.PENDING;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DbType getDbType() {
        return dbType;
    }

    public void setDbType(DbType dbType) {
        this.dbType = dbType;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public MockStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(MockStrategy strategy) {
        this.strategy = strategy;
    }

    public Map<String, FieldMockDef> getFieldDefinitions() {
        return fieldDefinitions;
    }

    public void setFieldDefinitions(Map<String, FieldMockDef> fieldDefinitions) {
        this.fieldDefinitions = fieldDefinitions;
    }

    public MockStatus getStatus() {
        return status;
    }

    public void setStatus(MockStatus status) {
        this.status = status;
    }

    public int getRecordsGenerated() {
        return recordsGenerated;
    }

    public void setRecordsGenerated(int recordsGenerated) {
        this.recordsGenerated = recordsGenerated;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public enum MockStrategy {
        RANDOM, SEQUENTIAL, PATTERN_BASED, REFERENCE_DATA
    }

    public enum MockStatus {
        PENDING, RUNNING, SUCCESS, FAILED
    }

    public static class FieldMockDef {
        private String fieldName;
        private String fieldType;
        private MockDataType dataType;
        private int minLength;
        private int maxLength;
        private String pattern;
        private List<String> enumValues;
        private Object minValue;
        private Object maxValue;
        private boolean nullable;
        private String defaultValue;
        private boolean unique;

        public FieldMockDef() {
        }

        public FieldMockDef(String fieldName, MockDataType dataType) {
            this.fieldName = fieldName;
            this.dataType = dataType;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFieldType() {
            return fieldType;
        }

        public void setFieldType(String fieldType) {
            this.fieldType = fieldType;
        }

        public MockDataType getDataType() {
            return dataType;
        }

        public void setDataType(MockDataType dataType) {
            this.dataType = dataType;
        }

        public int getMinLength() {
            return minLength;
        }

        public void setMinLength(int minLength) {
            this.minLength = minLength;
        }

        public int getMaxLength() {
            return maxLength;
        }

        public void setMaxLength(int maxLength) {
            this.maxLength = maxLength;
        }

        public String getPattern() {
            return pattern;
        }

        public void setPattern(String pattern) {
            this.pattern = pattern;
        }

        public List<String> getEnumValues() {
            return enumValues;
        }

        public void setEnumValues(List<String> enumValues) {
            this.enumValues = enumValues;
        }

        public Object getMinValue() {
            return minValue;
        }

        public void setMinValue(Object minValue) {
            this.minValue = minValue;
        }

        public Object getMaxValue() {
            return maxValue;
        }

        public void setMaxValue(Object maxValue) {
            this.maxValue = maxValue;
        }

        public boolean isNullable() {
            return nullable;
        }

        public void setNullable(boolean nullable) {
            this.nullable = nullable;
        }

        public String getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }

        public boolean isUnique() {
            return unique;
        }

        public void setUnique(boolean unique) {
            this.unique = unique;
        }
    }

    public enum MockDataType {
        FIRST_NAME, LAST_NAME, FULL_NAME, EMAIL, PHONE, ADDRESS, CITY, STATE, ZIP_CODE,
        UUID, URL, IP_ADDRESS, COLOR, COMPANY, JOB_TITLE,
        RANDOM_INT, RANDOM_LONG, RANDOM_DOUBLE, RANDOM_FLOAT, RANDOM_BIG_DECIMAL,
        CURRENCY_AMOUNT, PERCENTAGE,
        PAST_DATE, FUTURE_DATE, RANDOM_DATE, DATE_TIME, TIMESTAMP,
        BOOLEAN, PASSWORD, JSON_OBJECT, CUSTOM
    }
}