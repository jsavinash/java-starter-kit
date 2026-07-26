plugins {
    id("com.convention-plugins.java-app")
}

group = "com.iluwatar"
version = "1.0.0"

application {
    mainClass.set("com.iluwatar.activeobject.App")
}

dependencies {
    implementation(libs.slf4j.api)
    implementation(libs.logback.classic)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
}