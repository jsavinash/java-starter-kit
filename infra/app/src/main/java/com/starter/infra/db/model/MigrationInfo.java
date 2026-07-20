package com.starter.infra.db.model;

import com.starter.infra.db.dbtype.DbType;

import java.time.LocalDateTime;

public class MigrationInfo {
    private String id;
    private DbType dbType;
    private String databaseName;
    private String version;
    private String description;
    private String script;
    private String checksum;
    private MigrationType type;
    private MigrationStatus status;
    private String executedBy;
    private LocalDateTime executedAt;
    private int executionTimeMs;
    private String errorMessage;

    public MigrationInfo() {
    }

    public MigrationInfo(String id, DbType dbType, String databaseName, String version, String description,
            String script, MigrationType type) {
        this.id = id;
        this.dbType = dbType;
        this.databaseName = databaseName;
        this.version = version;
        this.description = description;
        this.script = script;
        this.type = type;
        this.status = MigrationStatus.PENDING;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public MigrationType getType() {
        return type;
    }

    public void setType(MigrationType type) {
        this.type = type;
    }

    public MigrationStatus getStatus() {
        return status;
    }

    public void setStatus(MigrationStatus status) {
        this.status = status;
    }

    public String getExecutedBy() {
        return executedBy;
    }

    public void setExecutedBy(String executedBy) {
        this.executedBy = executedBy;
    }

    public LocalDateTime getExecutedAt() {
        return executedAt;
    }

    public void setExecutedAt(LocalDateTime executedAt) {
        this.executedAt = executedAt;
    }

    public int getExecutionTimeMs() {
        return executionTimeMs;
    }

    public void setExecutionTimeMs(int executionTimeMs) {
        this.executionTimeMs = executionTimeMs;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public enum MigrationType {
        SCHEMA, DATA, UNDO, REPEATABLE
    }

    public enum MigrationStatus {
        PENDING, RUNNING, SUCCESS, FAILED, SKIPPED, UNDONE
    }
}