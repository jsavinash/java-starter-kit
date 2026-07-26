plugins {
    `kotlin-dsl` // <1>
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(project(":convention-plugins"))
    implementation(libs.springboot.gradle.plugin)
    implementation(libs.spotless.plugin)
    implementation(libs.detekt.gradle.plugin)
    implementation(libs.lombok.plugin)
}
