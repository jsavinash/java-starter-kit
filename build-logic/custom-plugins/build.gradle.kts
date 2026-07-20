plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.spotless.plugin)
    implementation(libs.detekt.gradle.plugin)
}
