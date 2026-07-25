plugins {
    id("java")
    id("jacoco")
    id("checkstyle")
    id("pmd")
    id("com.diffplug.spotless")
}

// ============================================================================
// Shared Build Logic - Applied to ALL subprojects
// ============================================================================

// Java Toolchain - Enforce Java 25 Amazon Corretto
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
        vendor.set(JvmVendorSpec.AMAZON)
    }
}

// Compiler Settings - Optimized for Java 25
tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release.set(25)
    options.compilerArgs.addAll(
        listOf(
            "-Xlint:all",
            "-Werror",
            "-parameters"
        )
    )
}

// Test Configuration - Optimized for Java 25
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    
    // JVM args for tests
    jvmArgs = listOf(
        "-Xmx512m",
        "-XX:+UseG1GC",
        "-XX:MaxMetaspaceSize=256m"
    )
}

// ============================================================================
// Code Quality - Spotless (Code Formatting)
// ============================================================================
spotless {
    java {
        // Lightweight checks to avoid reformatting existing educational code
        removeUnusedImports()
        trimTrailingWhitespace()
        endWithNewline()
    }
}

// ============================================================================
// Code Quality - Checkstyle
// ============================================================================
checkstyle {
    toolVersion = "10.25.0"
    // Use absolute path to ensure it works across composite builds
    configFile = file("${rootProject.projectDir}/../../config/checkstyle/checkstyle.xml")
    isIgnoreFailures = false
}

tasks.withType<Checkstyle>().configureEach {
    reports {
        html.required.set(true)
        xml.required.set(true)
    }
}

// ============================================================================
// Code Quality - PMD
// ============================================================================
pmd {
    toolVersion = "7.0.0"
    // Use absolute path to ensure it works across composite builds
    ruleSetFiles = files(file("${rootProject.projectDir}/../../config/pmd/pmd-ruleset.xml"))
    isIgnoreFailures = true
}

tasks.withType<Pmd>().configureEach {
    reports {
        html.required.set(true)
        xml.required.set(true)
    }
}

// ============================================================================
// Code Coverage - JaCoCo
// ============================================================================
jacoco {
    toolVersion = "0.8.13"
}

// JaCoCo test report task is automatically created by the jacoco plugin
// Configure it instead of creating a new one
tasks.named("jacocoTestReport") {
    group = "verification"
    description = "Generate JaCoCo test coverage report"
    dependsOn("test")
}

// ============================================================================
// Quality Gate Task (Aggregates all quality checks)
// ============================================================================
tasks.register("qualityGate") {
    group = "verification"
    description = "Runs all quality checks (Spotless, Checkstyle, PMD, Tests)"
    dependsOn(
        "spotlessCheck",
        "checkstyleMain",
        "checkstyleTest",
        "pmdMain",
        "pmdTest",
        "test"
    )
}

// ============================================================================
// Repository Configuration
// ============================================================================
repositories {
    mavenCentral()
}
