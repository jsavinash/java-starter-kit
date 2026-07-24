// ============================================================================
// Develocity Plugin - Build caching, build scans, and performance analytics
// NOTE: This plugin is applied at the settings level, not project level.
// The actual Develocity configuration is in the root settings.gradle.kts.
// This file provides helper tasks for build scan management.
// ============================================================================

// ============================================================================
// Build Scan Helper Tasks
// ============================================================================

// Task to publish a build scan
val publishBuildScan by tasks.registering {
    group = "help"
    description = "Publish a build scan for the current build"
    doLast {
        logger.lifecycle("📊 Build scan available. Run with --scan flag to publish.")
    }
}

// Task to check build cache status
val buildCacheStatus by tasks.registering {
    group = "help"
    description = "Display build cache statistics"
    doLast {
        val cacheDir = project.rootProject.rootDir.resolve(".gradle/build-cache")
        if (cacheDir.exists()) {
            val cacheSize = cacheDir.walkTopDown()
                .filter { it.isFile }
                .sumOf { it.length() }
            val cacheFiles = cacheDir.walkTopDown()
                .filter { it.isFile }
                .count()
            
            logger.lifecycle("📦 Build Cache Statistics:")
            logger.lifecycle("   Location: ${cacheDir.absolutePath}")
            logger.lifecycle("   Size: ${formatBytes(cacheSize)}")
            logger.lifecycle("   Files: $cacheFiles")
        } else {
            logger.lifecycle("📦 Build cache is empty or not yet created")
        }
    }
}

// ============================================================================
// Utility Functions
// ============================================================================

fun formatBytes(bytes: Long): String {
    val units = listOf("B", "KB", "MB", "GB")
    var size = bytes.toDouble()
    var unitIndex = 0
    while (size >= 1024 && unitIndex < units.size - 1) {
        size /= 1024
        unitIndex++
    }
    return "%.2f %s".format(size, units[unitIndex])
}

group = "com.custom-plugins.develocity"
version = "1.0.0"