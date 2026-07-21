plugins {
    java
    kotlin("jvm")
    id("checkstyle")
    id("com.custom-plugins.jacoco")
    id("com.custom-plugins.code-formatter")
    id("com.custom-plugins.detekt")
    id("com.custom-plugins.pmd")
    id("com.custom-plugins.auto-fix")
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
    description = "Run all quality checks: checkstyle, detekt, pmd, spotless, tests, coverage"
    dependsOn(
        tasks.check,
        tasks.named("checkstyleMain"),
        tasks.named("detektMain"),
        tasks.named("pmdMain"),
        tasks.named("spotlessCheck")
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
        tasks.named("spotlessCheck")
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

group = "com.custom-plugins.combined"
version = "1.0.0"
