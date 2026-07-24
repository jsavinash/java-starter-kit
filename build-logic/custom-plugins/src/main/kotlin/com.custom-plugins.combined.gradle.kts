plugins {
    java
    kotlin("jvm")
    id("checkstyle")
    id("com.custom-plugins.jacoco")
    id("com.custom-plugins.code-formatter")
    id("com.custom-plugins.detekt")
    id("com.custom-plugins.pmd")
    id("com.custom-plugins.javadoc2")
    id("com.custom-plugins.auto-fix")
    id("com.custom-plugins.dokka")
    id("com.custom-plugins.versions")
    id("com.custom-plugins.test-logger")
    id("com.custom-plugins.build-time-tracker")
    id("com.custom-plugins.dependency-analyze")
    id("com.custom-plugins.develocity")
}

kotlin {
    jvmToolchain(25)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

checkstyle {
    toolVersion = "10.25.0"
    var currentDir: java.io.File? = rootProject.rootDir
    while (currentDir != null && !currentDir.resolve("config/checkstyle/checkstyle.xml").exists()) {
        currentDir = currentDir.parentFile
    }
    configFile = currentDir?.resolve("config/checkstyle/checkstyle.xml") 
        ?: error("Could not find checkstyle.xml in any parent directory")
    maxErrors = 0
    maxWarnings = 0
    isIgnoreFailures = false
}

// ============================================================================
// Code Quality Gate - Fail build if any quality check fails
// ============================================================================

// Create a quality gate task that depends on all quality checks
val qualityGate by tasks.registering {
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

// ============================================================================
// Build Performance
// ============================================================================

// Generate build time report on every build
tasks.build {
    finalizedBy(tasks.matching { it.name == "buildTimeReport" })
}

group = "com.custom-plugins.combined"
version = "1.0.0"