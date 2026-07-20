package com.starter.infra.db.config;

import com.starter.infra.db.dbtype.DbType;
import com.starter.infra.db.model.DbConnectionInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class DbConfig {

    @Value("${infra.db.assets-path:../assets/deployment/database}")
    private String dbAssetsPath;

    @Bean
    public Map<String, DbConnectionInfo> dbConnections() {
        Map<String, DbConnectionInfo> conns = new LinkedHashMap<>();

        conns.put("postgres-master", new DbConnectionInfo(
                DbType.POSTGRES, "localhost", 5432, "postgres", "user", "pass"));

        conns.put("productdb", new DbConnectionInfo(
                DbType.POSTGRES, "localhost", 5432, "productdb", "user", "pass"));

        conns.put("orderdb", new DbConnectionInfo(
                DbType.POSTGRES, "localhost", 5432, "orderdb", "user", "pass"));

        conns.put("userdb", new DbConnectionInfo(
                DbType.POSTGRES, "localhost", 5432, "userdb", "user", "pass"));

        conns.put("inventorydb", new DbConnectionInfo(
                DbType.POSTGRES, "localhost", 5432, "inventorydb", "user", "pass"));

        conns.put("mongodb", new DbConnectionInfo(
                DbType.MONGODB, "localhost", 27017, "ecommerce", "admin", "pass"));

        conns.put("cassandra", new DbConnectionInfo(
                DbType.CASSANDRA, "localhost", 9042, "ecommerce", "cassandra", "cassandra"));

        return conns;
    }

    @Bean
    public String dbAssetsPath() {
        return dbAssetsPath;
    }
}