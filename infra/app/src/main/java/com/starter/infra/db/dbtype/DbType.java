package com.starter.infra.db.dbtype;

public enum DbType {
    POSTGRES("postgres", "PostgreSQL", 5432, "jdbc:postgresql://%s:%d/%s"),
    MONGODB("mongodb", "MongoDB", 27017, "mongodb://%s:%d/%s"),
    CASSANDRA("cassandra", "Cassandra", 9042, "cql://%s:%d/%s");

    private final String key;
    private final String displayName;
    private final int defaultPort;
    private final String connectionUrlTemplate;

    DbType(String key, String displayName, int defaultPort, String connectionUrlTemplate) {
        this.key = key;
        this.displayName = displayName;
        this.defaultPort = defaultPort;
        this.connectionUrlTemplate = connectionUrlTemplate;
    }

    public String getKey() {
        return key;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getDefaultPort() {
        return defaultPort;
    }

    public String getConnectionUrlTemplate() {
        return connectionUrlTemplate;
    }

    public static DbType fromKey(String key) {
        for (DbType t : values())
            if (t.key.equals(key))
                return t;
        throw new IllegalArgumentException("Unknown DbType: " + key);
    }
}