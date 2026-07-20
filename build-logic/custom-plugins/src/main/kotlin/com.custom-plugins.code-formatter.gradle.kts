plugins {
    id("com.diffplug.spotless")
}

spotless {
    java {
        googleJavaFormat()
    }
    kotlin {
        target("src/**/*.kt")
        ktlint()
    }
    kotlinGradle {
        target("*.gradle.kts")
        ktlint()
    }
}

group = "com.custom-plugins.java-lib"
version = "1.0.0"
