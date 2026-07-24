plugins {
    id("com.custom-plugins.java-library")
    alias(libs.plugins.lombok)
}

group = "com.iluwatar"
version = "1.0.0"

lombok {
    version.set(libs.versions.lombokLibrary.get())
}

dependencies {
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
}