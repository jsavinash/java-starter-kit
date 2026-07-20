package com.starter.infra.svc;

import com.starter.infra.model.ComponentSpec;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrchService {
    private static final Logger log = LoggerFactory.getLogger(OrchService.class);
    private final Map<String, ComponentSpec> components = new LinkedHashMap<>();

    @PostConstruct
    void init() {
        int o = 1;
        // Cache
        reg(o++, "redis", "Redis", "cache", "Redis 8.4.0", "docker/infrastructure/cache/redis/redis-setup.yaml",
                Map.of("Redis", 6379));
        // Database
        reg(o++, "postgres", "PostgreSQL", "db", "PostgreSQL 18.1",
                "docker/infrastructure/database/postgres/postgres-setup.yaml", Map.of("PG", 5432));
        reg(o++, "pgadmin", "pgAdmin", "db", "dpage/pgadmin4:9.11.0",
                "docker/infrastructure/database/postgres/postgres-setup.yaml", Map.of("Admin", 5050));
        reg(o++, "mongodb", "MongoDB", "db", "MongoDB 8.0", "docker/infrastructure/database/mongodb/mongodb-setup.yaml",
                Map.of("Mongo", 27017));
        reg(o++, "mongo-express", "MongoUI", "db", "mongo-express",
                "docker/infrastructure/database/mongodb/mongodb-setup.yaml", Map.of("UI", 8081));
        reg(o++, "cassandra", "Cassandra", "db", "Cassandra 5.0.6",
                "docker/infrastructure/database/cassandra/cassandra-setup.yaml", Map.of("CQL", 9042));
        // Messaging
        reg(o++, "zookeeper", "ZooKeeper", "mq", "Confluent ZK 7.5.0",
                "docker/infrastructure/message-queue/kafka/kafka-setup.yaml", Map.of("ZK", 2181));
        reg(o++, "kafka", "Kafka", "mq", "Confluent Kafka 7.5.0",
                "docker/infrastructure/message-queue/kafka/kafka-setup.yaml", Map.of("Kafka", 9092));
        reg(o++, "rabbitmq", "RabbitMQ", "mq", "RabbitMQ 3-mgmt",
                "docker/infrastructure/message-queue/rabbitmq/rabbitmq-setup.yaml", Map.of("AMQP", 5672, "UI", 15672));
        // IAM
        reg(o++, "keycloak", "Keycloak", "iam", "Keycloak 26.2.5",
                "docker/infrastructure/iam/keycloak/keycloak-setup.yaml", Map.of("KC", 8443));
        reg(o++, "vault", "Vault", "iam", "Vault 1.13.3", "docker/infrastructure/secret/vault/vault-setup.yaml",
                Map.of("Vault", 8200));
        // Monitoring
        reg(o++, "prometheus", "Prometheus", "mon", "Prometheus v2.44.0",
                "docker/infrastructure/lgtm-stack/lgtm-stack-setup.yaml", Map.of("Prom", 9090));
        reg(o++, "loki-read", "Loki R", "mon", "Grafana Loki", "docker/infrastructure/lgtm-stack/lgtm-stack-setup.yaml",
                Map.of("LokiR", 3101));
        reg(o++, "loki-write", "Loki W", "mon", "Grafana Loki",
                "docker/infrastructure/lgtm-stack/lgtm-stack-setup.yaml", Map.of("LokiW", 3102));
        reg(o++, "grafana", "Grafana", "mon", "Grafana", "docker/infrastructure/lgtm-stack/lgtm-stack-setup.yaml",
                Map.of("Graf", 3000));
        reg(o++, "zipkin", "Zipkin", "mon", "OpenZipkin", "docker/infrastructure/lgtm-stack/lgtm-stack-setup.yaml",
                Map.of("Zip", 9411));
        reg(o++, "minio", "MinIO", "mon", "MinIO", "docker/infrastructure/lgtm-stack/lgtm-stack-setup.yaml",
                Map.of("S3", 9000));
        reg(o++, "alloy", "Alloy", "mon", "Grafana Alloy", "docker/infrastructure/lgtm-stack/lgtm-stack-setup.yaml",
                Map.of("Alloy", 12345));
        reg(o++, "loki-gw", "Loki GW", "mon", "Nginx", "docker/infrastructure/lgtm-stack/lgtm-stack-setup.yaml",
                Map.of("GW", 3100));
        log.info("Loaded {} infra components", components.size());
    }

    private void reg(int o, String n, String dn, String cat, String img, String file, Map<String, Integer> ports) {
        var c = new ComponentSpec(n, dn, cat, img, "../assets/deployment/" + file, o, ports);
        components.put(n, c);
    }

    public List<ComponentSpec> all() {
        return List.copyOf(components.values());
    }

    public Map<String, List<ComponentSpec>> byCat() {
        return components.values().stream()
                .collect(Collectors.groupingBy(ComponentSpec::getCategory, LinkedHashMap::new, Collectors.toList()));
    }

    public List<ComponentSpec> byCat(String cat) {
        return components.values().stream().filter(c -> c.getCategory().equals(cat)).collect(Collectors.toList());
    }

    public ComponentSpec get(String name) {
        var c = components.get(name);
        if (c == null)
            throw new IllegalArgumentException("Unknown: " + name);
        return c;
    }

    public ComponentSpec start(String name) {
        var c = get(name);
        c.setStatus("running");
        return c;
    }

    public ComponentSpec stop(String name) {
        var c = get(name);
        c.setStatus("stopped");
        return c;
    }

    public List<ComponentSpec> startAll() {
        components.values().forEach(c -> c.setStatus("running"));
        return all();
    }

    public List<ComponentSpec> stopAll() {
        components.values().forEach(c -> c.setStatus("stopped"));
        return all();
    }
}