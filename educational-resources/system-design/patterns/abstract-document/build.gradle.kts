plugins {
    id("application")
    id("com.convention-plugins.code-lombok")
    id("com.convention-plugins.junit-platform")
}

group = "com.iluwatar"
version = "1.0.0"

dependencies {
    implementation(libs.slf4j.api)
    implementation(libs.logback.classic)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
}
