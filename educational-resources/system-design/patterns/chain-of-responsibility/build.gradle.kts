plugins {
    id("com.custom-plugins.java-app")
    alias(libs.plugins.lombok)
}

group = "com.iluwatar"
version = "1.0.0"

application {
    mainClass.set("com.iluwatar.chain.App")
}

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

tasks.jacocoTestCoverageVerification {
    violationRules.rules.first().limits.forEach { limit ->
        if (limit.counter == "INSTRUCTION") {
            limit.minimum = "0.70".toBigDecimal()
        }
    }
}