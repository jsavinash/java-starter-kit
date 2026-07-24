// ============================================================================
// Javadoc2 Plugin - Enforces comprehensive Javadoc documentation
// on Java source code with configurable severity levels.
// ============================================================================

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.VerificationTask
import org.gradle.api.file.FileCollection
import java.io.File

/**
 * Custom task that scans Java source files for missing Javadoc comments
 * on public classes, methods, and fields.
 */
abstract class Javadoc2CheckTask : DefaultTask(), VerificationTask {

    @get:Input
    var sourceFiles: FileCollection = project.files()

    @get:Input
    var severity: String = "ERROR"
    
    @get:Input
    var excludePatterns: List<String> = listOf(
        "**/build/**",
        "**/generated/**",
        "**/test/**"
    )

    @get:Input
    var failOnMissing: Boolean = true

    @get:OutputFile
    val reportFile: File = File(temporaryDir, "javadoc2-report.txt")

    private var ignoreFailures: Boolean = false
    
    override fun getIgnoreFailures(): Boolean = ignoreFailures
    
    override fun setIgnoreFailures(ignoreFailures: Boolean) {
        this.ignoreFailures = ignoreFailures
    }

    @TaskAction
    fun checkJavadoc() {
        val violations = mutableListOf<String>()
        val sourceFilesList = sourceFiles.files
            .filter { it.name.endsWith(".java") }
            .filter { file ->
                excludePatterns.none { pattern ->
                    val regex = pattern.replace("**", ".*").replace("*", "[^/]*")
                    file.path.contains(Regex(regex))
                }
            }

        logger.lifecycle("📝 Javadoc2: Scanning ${sourceFilesList.size} Java source files...")

        for (file in sourceFilesList.sorted()) {
            violations.addAll(analyzeFile(file))
        }

        reportFile.parentFile.mkdirs()
        reportFile.writeText(violations.joinToString("\n"))
        
        if (violations.isNotEmpty()) {
            logger.lifecycle("\n📋 Javadoc2 Report: ${violations.size} violations found")
            violations.forEach { logger.warn(it) }
            
            if (!ignoreFailures && failOnMissing) {
                throw GradleException(
                    "Javadoc2 check failed: ${violations.size} Javadoc violation(s) found.\n" +
                    "Report: ${reportFile.absolutePath}"
                )
            }
        } else {
            logger.lifecycle("✅ Javadoc2: All public API elements have Javadoc documentation")
        }
    }

    private fun analyzeFile(file: File): List<String> {
        val violations = mutableListOf<String>()
        val lines = file.readLines()
        val relativePath = file.relativeTo(project.projectDir).path
        var i = 0

        while (i < lines.size) {
            val line = lines[i].trim()
            
            when {
                line.startsWith("package ") || 
                line.startsWith("import ") ||
                line.startsWith("@") ||
                line.isEmpty() ||
                line.startsWith("//") ||
                line.startsWith("/*") ||
                line.startsWith("*") ||
                line.startsWith("/**") -> { i++; continue }
            }

            if (isPublicTypeDeclaration(line)) {
                val prevLine = getPreviousNonBlankLine(lines, i)
                if (!hasJavadoc(prevLine)) {
                    violations.add("MISSING JAVADOC: $relativePath:${i + 1} - Public type declaration: ${line.take(80)}")
                }
            }
            
            if (isPublicMethodDeclaration(line)) {
                val prevLine = getPreviousNonBlankLine(lines, i)
                if (!hasJavadoc(prevLine)) {
                    violations.add("MISSING JAVADOC: $relativePath:${i + 1} - Public method: ${line.take(80)}")
                }
            }

            if (isPublicFieldDeclaration(line)) {
                val prevLine = getPreviousNonBlankLine(lines, i)
                if (!hasJavadoc(prevLine)) {
                    violations.add("MISSING JAVADOC: $relativePath:${i + 1} - Public field: ${line.take(80)}")
                }
            }

            i++
        }

        return violations
    }

    private fun getPreviousNonBlankLine(lines: List<String>, currentIndex: Int): String {
        var idx = currentIndex - 1
        while (idx >= 0) {
            val line = lines[idx].trim()
            if (line.isNotEmpty() && !line.startsWith("@")) {
                return line
            }
            idx--
        }
        return ""
    }

    private fun hasJavadoc(previousLine: String): Boolean {
        return previousLine.trim().startsWith("/**") || 
               previousLine.trim().startsWith("/*") ||
               previousLine.trim().startsWith("*")
    }

    private fun isPublicTypeDeclaration(line: String): Boolean {
        val publicTypeRegex = Regex("""^\s*public\s+(abstract\s+|final\s+)?(class|interface|@interface|enum|record)\s""")
        return publicTypeRegex.containsMatchIn(line)
    }

    private fun isPublicMethodDeclaration(line: String): Boolean {
        val publicMethodRegex = Regex("""^\s*public\s+.*\(.*\)\s*\{?(\s*throws\s+\w+.*)?$""")
        if (publicMethodRegex.containsMatchIn(line) && line.contains("(") && line.contains(")")) {
            if (!line.contains("class ") && !line.contains("enum ") && !line.contains("interface ")) {
                return true
            }
        }
        return false
    }

    private fun isPublicFieldDeclaration(line: String): Boolean {
        val publicFieldRegex = Regex("""^\s*public\s+(static\s+|final\s+|volatile\s+|transient\s+)*\w+<.*>\s+\w+\s*[=;].*$""")
        val simplePublicFieldRegex = Regex("""^\s*public\s+(static\s+|final\s+)*\w+\s+\w+\s*[=;].*$""")
        return publicFieldRegex.containsMatchIn(line) || simplePublicFieldRegex.containsMatchIn(line)
    }
}

// ============================================================================
// Plugin application
// ============================================================================

val javadoc2Check by tasks.register<Javadoc2CheckTask>("javadoc2Check") {
    group = "verification"
    description = "Check that all public Java API has Javadoc documentation"
    sourceFiles = fileTree("src/main/java") {
        include("**/*.java")
    }
}

tasks.register("javadoc2Report") {
    group = "documentation"
    description = "Generate Javadoc2 violation report"
    dependsOn(javadoc2Check)
    doLast {
        val reportDir = layout.buildDirectory.dir("reports/javadoc2").get().asFile
        reportDir.mkdirs()
        logger.lifecycle("📋 Javadoc2 report directory: ${reportDir.absolutePath}")
    }
}
