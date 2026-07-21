// ============================================================================
// Root Build File for Java Starter Kit Monorepo
// Orchestrates tasks across all subprojects and composite builds
// ============================================================================

plugins {
    id("com.custom-plugins.code-formatter") apply false
}

// ============================================================================
// Git Hooks Auto-Installation
// Runs before every build to ensure hooks are active on fresh clones.
// ============================================================================
abstract class InstallGitHooksTask : DefaultTask() {
    @TaskAction
    fun install() {
        val hooksPath = project.rootProject.file(".githooks").absolutePath

        // Check current hooksPath git config
        val process = ProcessBuilder("git", "config", "--get", "core.hooksPath")
            .redirectErrorStream(true)
            .start()
        val currentPath = process.inputStream.bufferedReader().readText().trim()
        process.waitFor()

        if (currentPath != hooksPath) {
            logger.lifecycle("📎 Installing git hooks...")
            val installProcess = ProcessBuilder(
                "bash",
                project.rootProject.file("scripts/install-hooks.sh").absolutePath
            )
                .inheritIO()
                .start()
            val exitCode = installProcess.waitFor()
            if (exitCode == 0) {
                logger.lifecycle("✅ Git hooks installed ({})", hooksPath)
            } else {
                logger.warn("⚠️ Git hooks installation exited with code {}", exitCode)
            }
        }
    }
}

tasks.register<InstallGitHooksTask>("installGitHooks") {
    group = "build setup"
    description = "Install Git hooks from .githooks/ directory"
}

// Make key tasks depend on hook installation
tasks.matching { it.name in listOf("build", "compileKotlin", "compileJava") }.configureEach {
    dependsOn(":installGitHooks")
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