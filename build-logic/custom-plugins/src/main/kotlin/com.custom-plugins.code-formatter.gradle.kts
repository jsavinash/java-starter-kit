plugins {
    id("com.diffplug.spotless")
}

spotless {
    // Enforce charset
    encoding("UTF-8")

    // Java formatting - Google Java Style
    java {
        target("src/**/*.java")
        googleJavaFormat()
            .reorderImports(true)
        removeUnusedImports()
        trimTrailingWhitespace()
        endWithNewline()
        targetExclude("**/build/**", "**/generated/**", "**/bin/**")
    }

    // Kotlin formatting - ktlint
    kotlin {
        target("src/**/*.kt")
        ktlint()
            .setEditorConfigPath(rootProject.rootDir.resolve(".editorconfig").takeIf { it.exists() }
                ?: rootProject.parent?.rootDir?.resolve(".editorconfig"))
        trimTrailingWhitespace()
        endWithNewline()
        targetExclude("**/build/**", "**/generated/**", "**/bin/**")
    }

    // Kotlin Gradle DSL formatting
    kotlinGradle {
        target("*.gradle.kts", "**/*.gradle.kts")
        ktlint()
        trimTrailingWhitespace()
        endWithNewline()
    }

    // Gradle properties formatting
    format("gradleProperties") {
        target("**/*.properties")
        targetExclude("**/build/**", "**/gradle-wrapper.properties")
        trimTrailingWhitespace()
        endWithNewline()
    }

    // YAML/JSON formatting
    format("yaml") {
        target("**/*.yml", "**/*.yaml")
        targetExclude("**/build/**")
        trimTrailingWhitespace()
        endWithNewline()
    }

    // Markdown formatting
    format("markdown") {
        target("**/*.md")
        targetExclude("**/build/**")
        trimTrailingWhitespace()
        endWithNewline()
    }
}

group = "com.custom-plugins.java-lib"
version = "1.0.0"
