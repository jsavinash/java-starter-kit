// ============================================================================
// Git Hooks Auto-Install Plugin
// Automatically installs .githooks/ hooks on build to ensure hooks are
// active on fresh clones without manual setup.
// ============================================================================

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

/**
 * Task that installs git hooks from the project's .githooks/ directory
 * by running the install-hooks.sh script.
 */
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
            val hooksScript = findInstallScript()
            if (hooksScript == null) {
                logger.warn("⚠️  install-hooks.sh not found. Skipping git hooks installation.")
                return
            }
            val installProcess = ProcessBuilder("bash", hooksScript.absolutePath)
                .inheritIO()
                .start()
            val exitCode = installProcess.waitFor()
            if (exitCode == 0) {
                logger.lifecycle("✅ Git hooks installed ({})", hooksPath)
            } else {
                logger.warn("⚠️  Git hooks installation exited with code {}", exitCode)
            }
        }
    }

    /**
     * Searches for install-hooks.sh in the project root or scripts/ directory.
     */
    private fun findInstallScript(): File? {
        val projectRoot = project.rootProject.rootDir
        // Check scripts/install-hooks.sh first (preferred location)
        val scriptsPath = File(projectRoot, "scripts/install-hooks.sh")
        if (scriptsPath.exists()) return scriptsPath
        // Fallback: root-level install-hooks.sh
        val rootPath = File(projectRoot, "install-hooks.sh")
        if (rootPath.exists()) return rootPath
        return null
    }
}

// ============================================================================
// Plugin registration
// ============================================================================

val installGitHooksTask = tasks.register<InstallGitHooksTask>("installGitHooks") {
    group = "build setup"
    description = "Install Git hooks from .githooks/ directory"
}

// Auto-wire: make key build tasks depend on hook installation
tasks.matching { it.name in listOf("build", "compileKotlin", "compileJava") }.configureEach {
    dependsOn(installGitHooksTask)
}