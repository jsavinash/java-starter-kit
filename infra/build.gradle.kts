plugins { java }
description = "Infra deployment assets"
group = "com.starter.infra"
version = "0.0.1"

tasks.register("validateAssets") {
    group = "verification"; description = "Validate deployment assets"
    doLast {
        val assetsDir = project.projectDir.resolve("deployment")
        val required = listOf(
            "docker/infrastructure/docker-compose.yml",
            "docker/infrastructure/cache/redis/redis-setup.yaml",
            "docker/infrastructure/database/postgres/postgres-setup.yaml",
            "docker/infrastructure/database/mongodb/mongodb-setup.yaml",
            "docker/infrastructure/database/cassandra/cassandra-setup.yaml",
            "docker/infrastructure/message-queue/kafka/kafka-setup.yaml",
            "docker/infrastructure/message-queue/rabbitmq/rabbitmq-setup.yaml",
            "docker/infrastructure/iam/keycloak/keycloak-setup.yaml",
            "docker/infrastructure/secret/vault/vault-setup.yaml",
            "docker/infrastructure/lgtm-stack/lgtm-stack-setup.yaml",
            "docker/scripts/docker-compose-up.sh",
            "docker/scripts/docker-compose-down.sh",
            "kubernetes/apps/api-gateway.yaml",
            "kubernetes/infrastructure/cassandra.yaml",
            "terraform/main.tf"
        )
        val missing = required.filter { !assetsDir.resolve(it).exists() }
        if (missing.isNotEmpty()) { throw GradleException("Missing: ${missing.size} assets") }
        logger.lifecycle("✅ ${required.size} deployment assets validated")
    }
}
tasks.named("compileJava") { enabled = false }
tasks.named("jar") { enabled = false }