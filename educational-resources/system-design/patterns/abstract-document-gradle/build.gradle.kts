plugins {
    id("com.convention-plugins.java-app")
    alias(libs.plugins.lombok)
}

group = "com.systemdesign.patterns"
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
}

application {
    // Use the set() method with the modern property name
    mainClass.set("com.systemdesign.patterns.abstractdocument.App")
}
