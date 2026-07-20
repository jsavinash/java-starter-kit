plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.kotlin.jvm.gradle.plugin)
    implementation(libs.spotless.plugin)
    implementation(libs.detekt.gradle.plugin)
}
