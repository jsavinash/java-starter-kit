plugins {
    `kotlin-dsl`
    alias(libs.plugins.kotlin.jvm)
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    // Build plugin dependencies - using type-safe accessors
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.spotless.plugin)
    
    // Plugin dependencies for precompiled scripts
    implementation(libs.dokka.gradle.plugin)
    implementation(libs.benmanes.versions.plugin)
    implementation(libs.test.logger.plugin)
    implementation(libs.docker.remote.api.plugin)
    implementation(libs.postgresql)
}
