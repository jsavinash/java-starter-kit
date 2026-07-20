plugins {
    `kotlin-dsl` // <1>
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(libs.spotless.plugin)
    compileOnly(libs.detekt.gradle.plugin)
}
