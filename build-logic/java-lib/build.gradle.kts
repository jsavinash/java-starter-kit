plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.spotless.plugin)
    compileOnly(libs.detekt.gradle.plugin)
}
