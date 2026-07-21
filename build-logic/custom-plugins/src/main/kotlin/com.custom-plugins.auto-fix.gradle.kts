// ============================================================================
// Auto-Fix Plugin
// Automatically applies fixes when quality checks are configured:
//
// Features:
//   - spotlessCheck finalizedBy spotlessApply — auto-formats code
//   - checkstyleMain finalizedBy spotlessApply — fixes formatting violations
//   - checkstyleAutoFix custom task — fixes common checkstyle issues
//   - detektMain/detektTest finalizedBy detektAutoCorrect — auto-corrects
//   - qualityFix convenience task — runs all fix tools at once
// ============================================================================

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

// ============================================================================
// 1. Spotless: finalize check with auto-apply (idempotent & safe)
// ============================================================================
if (project.plugins.hasPlugin("com.diffplug.spotless")) {
    tasks.matching { it.name == "spotlessCheck" }.configureEach {
        finalizedBy("spotlessApply")
    }
}

// ============================================================================
// 2. Checkstyle: auto-fix common violations
//    Checkstyle has no built-in auto-correct, but many violations (formatting,
//    imports, whitespace, Javadoc style) are fixed by spotless. For deeper
//    issues, checkstyleAutoFix provides a custom fix task.
// ============================================================================
if (project.plugins.hasPlugin("checkstyle")) {
    // When checkstyle fails, attempt to fix via spotless
    tasks.matching { it.name == "checkstyleMain" }.configureEach {
        if (project.plugins.hasPlugin("com.diffplug.spotless")) {
            finalizedBy("spotlessApply")
        }
    }
}

/**
 * Custom task that fixes common Checkstyle violations automatically:
 *   - Removes trailing whitespace
 *   - Ensures files end with newline
 *   - Replaces tabs with spaces (4 spaces per tab)
 *   - Removes consecutive blank lines (max 1)
 * These are the most common checkstyle violations that can be auto-fixed
 * without needing a full formatter.
 */
abstract class CheckstyleAutoFixTask : DefaultTask() {

    @TaskAction
    fun fix() {
        val sourceSets = project.extensions.findByName("sourceSets")
        if (sourceSets == null) {
            logger.warn("⚠️  No source sets found — skipping checkstyle auto-fix")
            return
        }

        val srcDirs = mutableListOf<File>()
        // Collect all source directories from main source set
        val mainSourceSet = (sourceSets as org.gradle.api.NamedDomainObjectContainer<*>)
            .findByName("main")
        if (mainSourceSet != null) {
            val java = mainSourceSet.javaClass
            try {
                val allJava = java.getMethod("getAllJava").invoke(mainSourceSet)
                val srcDirsField = allJava.javaClass.getMethod("getSrcDirs")
                @Suppress("UNCHECKED_CAST")
                val dirs = srcDirsField.invoke(allJava) as Collection<File>
                srcDirs.addAll(dirs)
            } catch (e: Exception) {
                // Fallback: use convention-based src dir
                srcDirs.add(project.projectDir.resolve("src/main/java"))
            }
        }

        if (srcDirs.isEmpty()) {
            logger.warn("⚠️  No source directories found — skipping checkstyle auto-fix")
            return
        }

        var fixedCount = 0
        val extensions = setOf("java", "kt", "kts", "xml", "properties", "yml", "yaml")

        srcDirs.forEach { dir ->
            if (!dir.exists()) return@forEach
            dir.walkTopDown()
                .filter { it.isFile && it.extension in extensions }
                .forEach { file ->
                    val original = file.readText()
                    var content = original

                    // Fix 1: Remove trailing whitespace
                    content = content.replace(Regex("[ \\t]+$", RegexOption.MULTILINE), "")

                    // Fix 2: Ensure file ends with exactly one newline
                    content = content.trimEnd('\n', '\r') + "\n"

                    // Fix 3: Replace tabs with 4 spaces
                    content = content.replace("\t", "    ")

                    // Fix 4: Remove consecutive blank lines (max 1)
                    content = content.replace(Regex("\n{3,}"), "\n\n")

                    if (content != original) {
                        file.writeText(content)
                        fixedCount++
                    }
                }
        }

        if (fixedCount > 0) {
            logger.lifecycle("✅ Checkstyle auto-fix: fixed {} file(s)", fixedCount)
        } else {
            logger.lifecycle("✅ Checkstyle auto-fix: no files needed fixing")
        }
    }
}

// Register checkstyleAutoFix task
val checkstyleAutoFix by tasks.registering(CheckstyleAutoFixTask::class) {
    group = "verification"
    description = "Auto-fix common Checkstyle violations (trailing whitespace, tabs, newlines)"
}

// ============================================================================
// 3. Detekt: auto-correct on check tasks
// ============================================================================
listOf("detektMain", "detektTest").forEach { checkTask ->
    tasks.matching { it.name == checkTask }.configureEach {
        val autoCorrectName = if (checkTask == "detektMain") "detektAutoCorrect" else "detektTestAutoCorrect"
        if (project.tasks.findByName(autoCorrectName) != null) {
            finalizedBy(autoCorrectName)
        }
    }
}

// ============================================================================
// 4. qualityFix meta-task — one command to fix everything
// ============================================================================
val qualityFix by tasks.registering {
    group = "verification"
    description = "Apply all auto-fixes: spotlessApply + checkstyleAutoFix + detektAutoCorrect"
}

// Wire fix tasks into qualityFix
project.afterEvaluate {
    listOf("spotlessApply", "checkstyleAutoFix", "detektAutoCorrect").forEach { fixTaskName ->
        val fixTask = project.tasks.findByName(fixTaskName)
        if (fixTask != null) {
            qualityFix.configure { dependsOn(fixTask) }
        }
    }
}

group = "com.custom-plugins.auto-fix"
version = "1.0.0"