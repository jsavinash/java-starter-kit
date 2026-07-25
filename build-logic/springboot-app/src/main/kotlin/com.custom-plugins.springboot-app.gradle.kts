plugins {
    id("com.custom-plugins.combined")
    id("org.springframework.boot")
}

// Spring Boot application configuration
group = "com.starter.apps"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter:4.0.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test:4.0.1")
    testImplementation("org.junit.platform:junit-platform-launcher:6.0.2")
}

// Load .env file if present
val envFile = rootProject.file(".env")
if (envFile.exists()) {
    envFile.readLines().forEach { line ->
        if (line.isNotBlank() && !line.startsWith("#")) {
            val (key, value) = line.split("=", limit = 2)
            System.setProperty(key.trim(), value.trim())
        }
    }
}