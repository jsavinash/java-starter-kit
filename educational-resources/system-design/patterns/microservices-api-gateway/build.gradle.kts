plugins {
    id("com.custom-plugins.java-app")
    alias(libs.plugins.lombok)
}

group = "com.iluwatar"
version = "1.0.0"

lombok {
    version.set(libs.versions.lombokLibrary.get())
}

dependencies {
    implementation(libs.slf4j.api)
    implementation(libs.logback.classic)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)

    implementation(libs.springboot.starter.web)
    implementation(libs.spring.retry)
    implementation(libs.h2)
}
