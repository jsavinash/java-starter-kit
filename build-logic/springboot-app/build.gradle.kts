plugins {
    `kotlin-dsl` // <1>
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(project(":custom-plugins"))
    // Spring Boot plugin version managed by libs.versions.toml
    implementation("org.springframework.boot:org.springframework.boot.gradle.plugin:4.0.1")
    // Spotless is already in custom-plugins
}
