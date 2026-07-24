// ============================================================================
// Dependency Analysis Plugin - Analyzes project dependencies for best practices
// Detects unused, undeclared, and transitive dependencies.
// ============================================================================

plugins {
    id("ca.cutterslade.analyze")
}

// ============================================================================
// Dependency Analysis Configuration
// ============================================================================

// Configure dependency analysis
afterEvaluate {
    tasks.matching { it.name.startsWith("analyze") }.configureEach {
        group = "help"
        description = "Analyze project dependencies"
    }
}

// ============================================================================
// Custom Dependency Analysis Tasks
// ============================================================================

// Task to analyze all dependency configurations
val analyzeDependencies by tasks.registering {
    group = "help"
    description = "Analyze all project dependencies for issues"
    dependsOn(
        tasks.matching { it.name.startsWith("analyze") }
    )

    doLast {
        logger.lifecycle("✅ Dependency analysis complete")
    }
}

// ============================================================================
// Dependency Validation
// ============================================================================

// Validate that dependency versions are consistent
val validateDependencyVersions by tasks.registering {
    group = "help"
    description = "Validate dependency version consistency across the project"

    doLast {
        val dependencies = mutableMapOf<String, MutableMap<String, String>>()

        project.configurations.forEach { configuration ->
            if (configuration.isCanBeResolved) {
                configuration.dependencies.forEach { dependency ->
                    val key = "${dependency.group}:${dependency.name}"
                    val version = dependency.version ?: "unspecified"

                    dependencies.computeIfAbsent(key) { mutableMapOf() }
                        .merge(configuration.name, version) { old, _ -> old }
                }
            }
        }

        var conflictsFound = false
        dependencies.forEach { (key, versions) ->
            val uniqueVersions = versions.values.toSet()
            if (uniqueVersions.size > 1) {
                logger.warn("⚠️  Version conflict for $key:")
                versions.forEach { (config, version) ->
                    logger.warn("     $config -> $version")
                }
                conflictsFound = true
            }
        }

        if (conflictsFound) {
            logger.warn("\n⚠️  Dependency version conflicts detected. Consider using a BOM or version catalog.")
        } else {
            logger.lifecycle("✅ All dependency versions are consistent")
        }
    }
}

// ============================================================================
// Dependency Tree Visualization
// ============================================================================

val dependencyTree by tasks.registering {
    group = "help"
    description = "Display the full dependency tree for all configurations"
    dependsOn(
        tasks.matching { it.name == "dependencies" }
    )

    doLast {
        logger.lifecycle("📊 Dependency tree available via: ./gradlew dependencies")
    }
}

group = "com.custom-plugins.dependency-analyze"
version = "1.0.0"