plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(project(":custom-plugins"))
    implementation(libs.spotless.plugin)
    implementation(libs.detekt.gradle.plugin)
}
