plugins {
    id("com.custom-plugins.java-app")
    alias(libs.plugins.lombok)
}

group = "com.iluwatar"
version = "1.0.0"

application {
    mainClass.set("com.iluwatar.backpressure.App")
}

lombok {
    version.set(libs.versions.lombokLibrary.get())
}

dependencies {
    implementation("io.projectreactor:reactor-core:3.8.0-M1")
    implementation(libs.logback.classic)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
    testImplementation("io.projectreactor:reactor-test:3.8.0-RC1")
}

tasks.jacocoTestCoverageVerification {
    violationRules.rules.first().limits.forEach { limit ->
        if (limit.counter == "INSTRUCTION") {
            limit.minimum = "0.70".toBigDecimal()
        }
    }
}