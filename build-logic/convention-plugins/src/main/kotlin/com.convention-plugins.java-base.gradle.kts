plugins {
    id("java")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
        vendor.set(JvmVendorSpec.AMAZON)
    }
}

configurations {
    compileClasspath {
        useLatestVersionsAndLock()
    }
    runtimeClasspath {
        useLatestVersionsAndLock()
    }
    testCompileClasspath {
        useLatestVersionsAndLock()
    }
    testRuntimeClasspath {
        useLatestVersionsAndLock()
    }
}

fun Configuration.useLatestVersionsAndLock() {
    resolutionStrategy {
        eachDependency {
            if (requested.version.isNullOrEmpty()) { useVersion("latest.release") }
        }
        activateDependencyLocking()
    }
}

dependencies.components.all {
    val lcVersion = id.version.lowercase()
    if (lcVersion.contains("alpha")
        || lcVersion.contains("-b")
        || lcVersion.contains("beta")
        || lcVersion.contains("cr")
        || lcVersion.contains("m")
        || lcVersion.contains("rc")
        || lcVersion.startsWith("200")) {

        // Tell Gradle to not treat pre-releases as 'release'
        status = "integration"
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(25) // Restrict syntax and bytecode to Java 25 APIs
}
