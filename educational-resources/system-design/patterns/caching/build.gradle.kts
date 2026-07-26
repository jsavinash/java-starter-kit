plugins {
    id("application")
    id("com.convention-plugins.code-lombok")
    id("com.convention-plugins.junit-platform")
}

group = "com.iluwatar"
version = "1.0.0"

application {
    mainClass.set("com.iluwatar.caching.App")
}

dependencies {
    implementation(libs.slf4j.api)
    implementation(libs.logback.classic)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.mockito) {
        isTransitive = false
    }
    testImplementation("org.mockito:mockito-inline:5.2.0")
    testRuntimeOnly(libs.junit.platform.launcher)

    implementation(libs.mongodb.driver.legacy)
    implementation(libs.h2)
    implementation(libs.gson)
}
