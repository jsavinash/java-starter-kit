// ============================================================================
// Java Starter Kit - Monorepo Root Settings
// ============================================================================

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    includeBuild("build-logic")
}

// ============================================================================
// Develocity Configuration (Build Scans & Build Cache)
// ============================================================================
plugins {
    id("com.gradle.develocity") version "4.5.0"
}

develocity {
    buildScan {
        publishing.onlyIf { System.getenv("CI").isNullOrEmpty().not() }
        uploadInBackground = !System.getenv("CI").isNullOrEmpty()
        tag(if (System.getenv("CI").isNullOrEmpty()) "local" else "CI")
        tag("java-starter-kit")
        
        // Add Git branch information
        val gitBranch = try {
            val process = ProcessBuilder("git", "rev-parse", "--abbrev-ref", "HEAD")
                .directory(rootProject.projectDir)
                .redirectErrorStream(true)
                .start()
            process.inputStream.bufferedReader().readText().trim()
        } catch (e: Exception) {
            "unknown"
        }
        tag("branch-$gitBranch")
        
        val gitCommitId = try {
            val process = ProcessBuilder("git", "rev-parse", "--short", "HEAD")
                .directory(rootProject.projectDir)
                .redirectErrorStream(true)
                .start()
            process.inputStream.bufferedReader().readText().trim()
        } catch (e: Exception) {
            "unknown"
        }
        value("Git Commit ID", gitCommitId)
        value("Git Branch", gitBranch)
        link("GitHub Repository", "https://github.com/jsavinash/java-starter-kit")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "java-starter-kit"

// == Composite Builds (isolated Gradle projects included in the main build) ==

// Infrastructure
includeBuild("infra")

// Micro-services composite build
includeBuild("apps/micro-services")

// Infrastructure Manager composite build
includeBuild("infra")

// Shared libraries composite build
includeBuild("shared")

// Aggregation (coverage reports, etc.)
includeBuild("aggregation")

// Packages
includeBuild("packages")

// Educational resources
includeBuild("educational-resources")

// Platforms BOM composite build
includeBuild("platforms")

// == Root-level subprojects ==
// These are convenience projects that aggregate from composite builds

// == Build Cache Configuration ==
buildCache {
    local {
        isEnabled = true
        directory = File(rootDir, ".gradle/build-cache")
    }
}
