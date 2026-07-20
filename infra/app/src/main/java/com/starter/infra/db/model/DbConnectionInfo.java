package com.starter.infra.db.model;

import com.starter.infra.db.dbtype.DbType;

public class DbConnectionInfo {
    private DbType dbType;
    private String host;
    private int port;
    private String database;
    private String username;
    private String password;
    private String connectionUrl;
    private boolean connected;
    private String version;

    public DbConnectionInfo() {
    }

    public DbConnectionInfo(DbType dbType, String host, int port, String database, String username, String password) {
        this.dbType = dbType;
        this.host = host;
        this.port = port > 0 ? port : dbType.getDefaultPort();
        this.database = database;
        this.username = username;
        this.password = password;
        this.connectionUrl = String.format(dbType.getConnectionUrlTemplate(), host, this.port, database);
    }

    public DbType getDbType() {
        return dbType;
    }

    public void setDbType(DbType dbType) {
        this.dbType = dbType;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}