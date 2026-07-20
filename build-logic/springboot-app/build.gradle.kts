plugins {
    `kotlin-dsl` // <1>
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":custom-plugins"))
    implementation(libs.springboot.gradle.plugin)
    implementation(libs.spotless.plugin)
    implementation(libs.detekt.gradle.plugin)
}
