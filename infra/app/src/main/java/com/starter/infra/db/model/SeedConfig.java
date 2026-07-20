package com.starter.infra.db.model;

import com.starter.infra.db.dbtype.DbType;

import java.util.List;
import java.util.Map;

public class SeedConfig {
    private String id;
    private DbType dbType;
    private String databaseName;
    private String name;
    private String description;
    private SeedSourceType sourceType;
    private String sourcePath;
    private Map<String, String> tableMapping;
    private boolean truncateBefore;
    private int batchSize;
    private SeedStatus status;
    private int recordsInserted;
    private String errorMessage;

    public SeedConfig() {
    }

    public SeedConfig(String id, DbType dbType, String databaseName, String name, SeedSourceType sourceType,
            String sourcePath) {
        this.id = id;
        this.dbType = dbType;
        this.databaseName = databaseName;
        this.name = name;
        this.sourceType = sourceType;
        this.sourcePath = sourcePath;
        this.truncateBefore = false;
        this.batchSize = 100;
        this.status = SeedStatus.PENDING;
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

    public SeedSourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(SeedSourceType sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public Map<String, String> getTableMapping() {
        return tableMapping;
    }

    public void setTableMapping(Map<String, String> tableMapping) {
        this.tableMapping = tableMapping;
    }

    public boolean isTruncateBefore() {
        return truncateBefore;
    }

    public void setTruncateBefore(boolean truncateBefore) {
        this.truncateBefore = truncateBefore;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public SeedStatus getStatus() {
        return status;
    }

    public void setStatus(SeedStatus status) {
        this.status = status;
    }

    public int getRecordsInserted() {
        return recordsInserted;
    }

    public void setRecordsInserted(int recordsInserted) {
        this.recordsInserted = recordsInserted;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public enum SeedSourceType {
        SQL, JSON, CSV, YAML, CQL
    }

    public enum SeedStatus {
        PENDING, RUNNING, SUCCESS, FAILED
    }
}