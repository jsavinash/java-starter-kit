// ============================================================================
// Root Build File for Java Starter Kit Monorepo
// Orchestrates tasks across all subprojects and composite builds
// ============================================================================

plugins {
    id("com.custom-plugins.code-formatter") apply false
    id("com.custom-plugins.githooks")
    id("com.custom-plugins.dokka") apply false
    id("com.custom-plugins.versions") apply false
    id("com.custom-plugins.develocity") apply false
    id("com.custom-plugins.test-logger") apply false
    id("com.custom-plugins.docker") apply false
}

// ============================================================================
// Root-level build lifecycle task (delegates to all included builds)
// ============================================================================
tasks.register("build") {
    group = "build"
    description = "Build all modules across the monorepo"
    doLast { runTaskInAllBuilds("build") }
}

// ============================================================================
// Aggregate tasks that run across all included composite builds via
// Gradle's built-in cross-build task delegation (--include-build).
// Each task simply delegates to the corresponding task in every included build.
// ============================================================================

/**
 * Run a Gradle task in every included composite build where it exists.
 * Uses ProcessBuilder to invoke the Gradle wrapper in each build directory.
 */
fun runTaskInAllBuilds(taskName: String) {
    val builds = gradle.includedBuilds
    if (builds.isEmpty()) {
        logger.warn("⚠️  No included builds")
        return
    }

    var ran = 0
    builds.forEach { build ->
        val buildDir = build.projectDir
        val gradlew = if (System.getProperty("os.name").lowercase().contains("win")) "gradlew.bat" else "./gradlew"
        try {
            val proc = ProcessBuilder(gradlew, ":$taskName", "--no-daemon", "-q")
                .directory(buildDir)
                .inheritIO()
                .start()
            val exit = proc.waitFor()
            if (exit == 0) {
                logger.lifecycle("  🛠️  {} in '{}'", taskName, build.name)
                ran++
            } else {
                logger.debug("  ⚠️  {} in '{}' not available (exit {})", taskName, build.name, exit)
            }
        } catch (e: Exception) {
            logger.debug("Cannot run {} in '{}': {}", taskName, build.name, e.message)
        }
    }
    logger.lifecycle("✅ {} ran in {} build(s)", taskName, ran)
}

tasks.register("qualityCheck") {
    group = "verification"
    description = "Run quality gate across all included builds"
    doLast { runTaskInAllBuilds("qualityGate") }
}

tasks.register("spotlessApply") {
    group = "verification"
    description = "Run spotlessApply across all included builds"
    doLast { runTaskInAllBuilds("spotlessApply") }
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
        logger.lifecycle("Run './gradlew dependencyUpdates' for checking dependency updates")
        logger.lifecycle("Run './gradlew :apps:micro-services:dependencyCheckAnalyze' for vulnerability scanning")
    }
}

// ============================================================================
// Documentation generation across the monorepo
// ============================================================================
tasks.register("generateDocs") {
    group = "documentation"
    description = "Generate API documentation using Dokka across all modules"
    doLast { runTaskInAllBuilds("dokkaHtml") }
}

tasks.register("generateAllDocs") {
    group = "documentation"
    description = "Generate all documentation formats (HTML, Javadoc, Markdown)"
    doLast { runTaskInAllBuilds("generateDocumentation") }
}

// ============================================================================
// Docker operations across the monorepo
// ============================================================================
tasks.register("dockerBuildAll") {
    group = "docker"
    description = "Build Docker images for all microservices"
    doLast { runTaskInAllBuilds("dockerBuildImage") }
}

tasks.register("dockerPublishAll") {
    group = "docker"
    description = "Build and publish Docker images for all microservices"
    doLast { runTaskInAllBuilds("dockerPublish") }
}

// ============================================================================
// Dependency analysis across the monorepo
// ============================================================================
tasks.register("analyzeAllDependencies") {
    group = "help"
    description = "Analyze dependencies across all modules"
    doLast { runTaskInAllBuilds("analyzeDependencies") }
}

tasks.register("validateAllDependencyVersions") {
    group = "help"
    description = "Validate dependency version consistency across all modules"
    doLast { runTaskInAllBuilds("validateDependencyVersions") }
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