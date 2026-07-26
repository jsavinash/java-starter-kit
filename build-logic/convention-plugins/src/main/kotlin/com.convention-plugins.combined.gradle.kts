plugins {
    kotlin("jvm")
    id("com.convention-plugins.java-base")
    id("com.convention-plugins.jacoco")
    id("com.convention-plugins.code-formatter")
    id("com.convention-plugins.detekt")
    id("com.convention-plugins.pmd")
    id("com.convention-plugins.javadoc2")
    id("com.convention-plugins.dokka")
    id("com.convention-plugins.versions")
    id("com.convention-plugins.test-logger")
    id("com.convention-plugins.develocity")
    //id("com.convention-plugins.lombok")
    id("com.convention-plugins.checkstyle")
}

kotlin {
    jvmToolchain(25)
}

// ============================================================================
// Code Quality Gate - Fail build if any quality check fails
// ============================================================================

// Create a quality gate task that depends on all quality checks
val qualityGate = tasks.register("qualityGate") {
    group = "verification"
    description = "Run all quality checks: checkstyle, detekt, pmd, spotless, javadoc2, tests, coverage"
    dependsOn(
        tasks.check,
        tasks.named("checkstyleMain"),
        tasks.named("detektMain"),
        tasks.named("pmdMain"),
        tasks.named("spotlessCheck"),
        tasks.named("javadoc2Check")
    )

    doLast {
        logger.lifecycle("✅ Quality gate passed: all checks successful")
    }
}

// Ensure quality gate runs on every build
tasks.check {
    dependsOn(
        tasks.named("checkstyleMain"),
        tasks.named("detektMain"),
        tasks.named("pmdMain"),
        tasks.named("spotlessCheck"),
        tasks.named("javadoc2Check")
    )
}

// ============================================================================
// Test Configuration - Enforce test execution
// ============================================================================
tasks.withType<Test>().configureEach {
    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed")
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        showExceptions = true
        showCauses = true
        showStackTraces = true
        showStandardStreams = false
    }

    // Fail build on test failure
    ignoreFailures = false

    // System properties for test execution
    systemProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager")
    systemProperty("file.encoding", "UTF-8")
}

// ============================================================================
// Documentation Generation
// ============================================================================

// Ensure documentation is generated as part of the build
tasks.build {
    dependsOn(tasks.matching { it.name.startsWith("dokka") })
}

// ============================================================================
// Dependency Management
// ============================================================================

// Check for dependency updates on every build (non-blocking)
tasks.build {
    finalizedBy(tasks.matching { it.name == "dependencyUpdates" })
}

group = "com.convention-plugins.combined"
version = "1.0.0"
