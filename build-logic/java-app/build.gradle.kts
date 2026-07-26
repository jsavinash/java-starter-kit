plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(project(":convention-plugins"))
    implementation(libs.spotless.plugin)
    implementation(libs.detekt.gradle.plugin)
    implementation(libs.lombok.plugin)
}
