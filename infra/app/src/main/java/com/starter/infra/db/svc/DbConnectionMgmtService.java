package com.starter.infra.db.svc;

import com.starter.infra.db.dbtype.DbType;
import com.starter.infra.db.model.DbConnectionInfo;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DbConnectionMgmtService {
    private static final Logger log = LoggerFactory.getLogger(DbConnectionMgmtService.class);
    private final Map<String, DbConnectionInfo> connections;
    private final Map<String, Connection> activeConnections = new ConcurrentHashMap<>();

    public DbConnectionMgmtService(Map<String, DbConnectionInfo> connections) {
        this.connections = connections;
    }

    @PostConstruct
    void init() {
        log.info("DbConnectionMgmtService initialized with {} connection definitions", connections.size());
    }

    public List<DbConnectionInfo> getAllConnections() {
        List<DbConnectionInfo> list = new ArrayList<>();
        for (var entry : connections.entrySet()) {
            var info = entry.getValue();
            info.setConnected(activeConnections.containsKey(entry.getKey()));
            list.add(info);
        }
        return list;
    }

    public DbConnectionInfo getConnection(String name) {
        var info = connections.get(name);
        if (info == null)
            throw new IllegalArgumentException("Unknown connection: " + name);
        info.setConnected(activeConnections.containsKey(name));
        return info;
    }

    public Map<String, Object> testConnection(String name) {
        var info = getConnection(name);
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("connection", name);
        result.put("dbType", info.getDbType().getDisplayName());
        result.put("host", info.getHost());
        result.put("port", info.getPort());
        result.put("database", info.getDatabase());

        try {
            boolean reachable = isHostReachable(info.getHost(), info.getPort());
            result.put("hostReachable", reachable);

            if (reachable) {
                var conn = tryConnect(info);
                if (conn != null) {
                    result.put("connected", true);
                    result.put("version", conn.getMetaData().getDatabaseProductVersion());
                    conn.close();
                } else {
                    result.put("connected", false);
                    result.put("error", "Authentication or database access failed");
                }
            } else {
                result.put("connected", false);
                result.put("error", "Host not reachable");
            }
        } catch (Exception e) {
            result.put("connected", false);
            result.put("error", e.getMessage());
        }
        return result;
    }

    public Map<String, Object> testAllConnections() {
        Map<String, Object> results = new LinkedHashMap<>();
        for (String name : connections.keySet()) {
            results.put(name, testConnection(name));
        }
        return results;
    }

    public Map<String, Object> getConnectionStatus() {
        Map<String, Object> status = new LinkedHashMap<>();
        int total = connections.size();
        int connected = activeConnections.size();
        status.put("total", total);
        status.put("active", connected);
        status.put("inactive", total - connected);
        status.put("connections", getAllConnections());
        return status;
    }

    private boolean isHostReachable(String host, int port) {
        try (var s = new Socket(host, port)) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Connection tryConnect(DbConnectionInfo info) {
        try {
            if (info.getDbType() == DbType.POSTGRES) {
                Class.forName("org.postgresql.Driver");
                return DriverManager.getConnection(
                        String.format("jdbc:postgresql://%s:%d/%s", info.getHost(), info.getPort(), info.getDatabase()),
                        info.getUsername(), info.getPassword());
            }
        } catch (Exception e) {
            log.debug("Connection failed for {}: {}", info.getDatabase(), e.getMessage());
        }
        return null;
    }

    public boolean executeRawSql(String connectionName, String sql) {
        var info = getConnection(connectionName);
        try (var conn = tryConnect(info)) {
            if (conn != null) {
                try (var stmt = conn.createStatement()) {
                    stmt.execute(sql);
                    return true;
                }
            }
        } catch (Exception e) {
            log.error("SQL execution failed on {}: {}", connectionName, e.getMessage());
        }
        return false;
    }

    public List<Map<String, Object>> queryRaw(String connectionName, String sql) {
        var info = getConnection(connectionName);
        List<Map<String, Object>> rows = new ArrayList<>();
        try (var conn = tryConnect(info)) {
            if (conn != null) {
                try (var stmt = conn.createStatement(); var rs = stmt.executeQuery(sql)) {
                    var meta = rs.getMetaData();
                    int cols = meta.getColumnCount();
                    while (rs.next()) {
                        Map<String, Object> row = new LinkedHashMap<>();
                        for (int i = 1; i <= cols; i++) {
                            row.put(meta.getColumnName(i), rs.getObject(i));
                        }
                        rows.add(row);
                    }
                }
            }
        } catch (Exception e) {
            log.error("Query failed on {}: {}", connectionName, e.getMessage());
        }
        return rows;
    }

    public List<String> listDatabases(String connectionName) {
        List<String> dbs = new ArrayList<>();
        try {
            var result = queryRaw(connectionName,
                    "SELECT datname FROM pg_database WHERE datistemplate = false ORDER BY datname");
            for (var row : result) {
                dbs.add(String.valueOf(row.get("datname")));
            }
        } catch (Exception e) {
            log.error("Failed to list databases: {}", e.getMessage());
        }
        return dbs;
    }
}