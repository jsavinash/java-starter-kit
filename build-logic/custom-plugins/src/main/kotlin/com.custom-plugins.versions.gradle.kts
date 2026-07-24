// ============================================================================
// Versions Plugin - Dependency update checking using ben-manes/gradle-versions-plugin
// Provides tasks to check for available dependency updates across the monorepo.
// ============================================================================

plugins {
    id("com.github.ben-manes.versions")
}

// ============================================================================
// Dependency Updates Configuration
// ============================================================================

// Configure the dependency updates task
tasks.named("dependencyUpdates") {
    group = "help"
    description = "Check for available dependency updates"
}

// ============================================================================
// Custom tasks for dependency management
// ============================================================================

// Task to display current dependency versions
val dependencyVersions = tasks.register("dependencyVersions") {
    group = "help"
    description = "Display current dependency versions"
    dependsOn(tasks.named("dependencyUpdates"))
}

// ============================================================================
// Integration with root build
// ============================================================================

// Register a root-level task for checking all dependencies
if (project == rootProject) {
    tasks.register("checkAllDependencies") {
        group = "help"
        description = "Check for dependency updates across all modules"
        dependsOn(tasks.named("dependencyUpdates"))
    }
}

group = "com.custom-plugins.versions"
version = "1.0.0"