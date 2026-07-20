plugins {
    id("com.custom-plugins.combined")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// ── .env File Loading for Local Development ───────────────────────────────────
fun loadEnvFile(): Map<String, String> {
    var dir: java.io.File? = rootProject.rootDir
    while (dir != null && !dir.resolve(".env").exists()) {
        dir = dir.parentFile
    }
    val envFile = dir?.resolve(".env") ?: return emptyMap()
    return envFile.readLines()
        .filter { it.isNotBlank() && !it.startsWith("#") && it.contains("=") }
        .associate { line ->
            val (key, value) = line.split("=", limit = 2)
            key.trim() to value.trim()
        }
}

tasks.withType<org.springframework.boot.gradle.tasks.run.BootRun>().configureEach {
    val envVars = loadEnvFile()
    if (envVars.isNotEmpty()) {
        environment(envVars)
    }
}
