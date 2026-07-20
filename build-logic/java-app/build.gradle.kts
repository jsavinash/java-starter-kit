plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":custom-plugins"))
    implementation(libs.spotless.plugin)
    implementation(libs.detekt.gradle.plugin)
}
