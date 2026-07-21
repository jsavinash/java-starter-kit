// ============================================================================
// Root Build File for Java Starter Kit Monorepo
// Orchestrates tasks across all subprojects and composite builds
// ============================================================================

plugins {
    id("com.custom-plugins.code-formatter") apply false
    id("com.custom-plugins.githooks")
}

// ============================================================================
// Root-level aggregation tasks for unified build commands
// ============================================================================

val qualityTasks = listOf(
    "spotlessCheck",
    "checkstyleMain",
    "detektMain",
    "pmdMain",
    "test",
    "jacocoTestCoverageVerification"
)

// Register root-level tasks that delegate to all subprojects
tasks.register("qualityCheck") {
    group = "verification"
    description = "Run all quality checks across the entire monorepo"
    dependsOn(
        subprojects.mapNotNull { it.tasks.findByName("qualityGate") }
            + subprojects.mapNotNull { it.tasks.findByName("check") }
    )
    doLast {
        logger.lifecycle("✅ Root quality check complete")
    }
}

tasks.register("fullBuild") {
    group = "build"
    description = "Full build of all modules with quality checks"
    dependsOn(tasks.named("qualityCheck"))
    doLast {
        logger.lifecycle("✅ Full build complete")
    }
}

// ============================================================================
// Unified test reporting
// ============================================================================
tasks.register("testReport") {
    group = "verification"
    description = "Aggregate test reports from all subprojects"
    doLast {
        val testResultsDir = layout.buildDirectory.dir("reports/tests").get().asFile
        testResultsDir.mkdirs()
        
        subprojects.forEach { subproject ->
            val subprojectTestReport = subproject.layout.buildDirectory
                .dir("reports/tests/test")
                .get().asFile
            if (subprojectTestReport.exists()) {
                logger.lifecycle("  📊 Test report: ${subproject.name} -> ${subprojectTestReport}")
            }
        }
    }
}

// ============================================================================
// Dependency management across the monorepo
// ============================================================================
tasks.register("dependencyUpdates") {
    group = "help"
    description = "Check for dependency updates across all modules"
    doLast {
        logger.lifecycle("Run './gradlew :apps:micro-services:dependencyCheckAnalyze' for vulnerability scanning")
    }
}

// ============================================================================
// Clean all build artifacts
// ============================================================================
tasks.register("deepClean") {
    group = "build"
    description = "Clean all build artifacts including caches"
    dependsOn(tasks.named("clean"))
    doLast {
        delete(
            file(".gradle"),
            file("build"),
            file("apps/micro-services/.gradle"),
            file("shared/.gradle"),
            file("aggregation/.gradle"),
            file("build-logic/.gradle")
        )
        logger.lifecycle("✅ Deep clean complete")
    }
}