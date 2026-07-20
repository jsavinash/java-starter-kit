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

// Shared libraries composite build
includeBuild("shared")

// Aggregation (coverage reports, etc.)
includeBuild("aggregation")

// Packages
includeBuild("packages")

// == Root-level subprojects ==
// These are convenience projects that aggregate from composite builds

// == Build Cache Configuration ==
buildCache {
    local {
        isEnabled = true
        directory = File(rootDir, ".gradle/build-cache")
    }
}
