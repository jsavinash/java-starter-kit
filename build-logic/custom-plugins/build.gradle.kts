plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.kotlin.jvm.gradle.plugin)
    implementation(libs.spotless.plugin)
    implementation(libs.detekt.gradle.plugin)

    // New plugin dependencies
    implementation(libs.dokka.gradle.plugin)
    implementation(libs.benmanes.versions.plugin)
    implementation(libs.develocity.gradle.plugin)
    implementation(libs.test.logger.plugin)
    implementation(libs.docker.remote.api.plugin)
}