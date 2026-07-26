plugins {
    id("com.convention-plugins.java-app")
    alias(libs.plugins.lombok)
}

group = "com.iluwatar"
version = "1.0.0"

application {
    mainClass.set("com.iluwatar.caching.App")
}

lombok {
    version.set(libs.versions.lombokLibrary.get())
}

dependencies {
    implementation(libs.slf4j.api)
    implementation(libs.logback.classic)
    implementation("org.mongodb:bson:5.4.0")
    implementation("org.mongodb:mongodb-driver-legacy:5.4.0")

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
    testImplementation("org.mockito:mockito-core:5.17.0")
}

tasks.jacocoTestCoverageVerification {
    violationRules.rules.first().limits.forEach { limit ->
        if (limit.counter == "INSTRUCTION") {
            limit.minimum = "0.70".toBigDecimal()
        }
    }
}