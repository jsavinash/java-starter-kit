// ============================================================================
// Build Time Tracker Plugin - Monitors and reports build performance
// Tracks build times, identifies slow tasks, and helps optimize builds.
// ============================================================================

import java.time.Duration
import java.time.Instant
import java.util.concurrent.ConcurrentHashMap

// ============================================================================
// Build Time Tracking
// ============================================================================

val taskExecutionTimes = ConcurrentHashMap<String, MutableList<Duration>>()
val buildStartTime = Instant.now()

// ============================================================================
// Task Execution Listener
// ============================================================================

gradle.taskGraph.whenReady {
    logger.lifecycle("⏱️  Build time tracking enabled")
}

gradle.addListener(object : org.gradle.api.execution.TaskExecutionListener {
    override fun beforeExecute(task: org.gradle.api.Task) {
        task.extensions.extraProperties.set("startTime", Instant.now())
    }

    override fun afterExecute(task: org.gradle.api.Task, state: org.gradle.api.tasks.TaskState) {
        val startTime = task.extensions.extraProperties.get("startTime") as? Instant
        if (startTime != null) {
            val duration = Duration.between(startTime, Instant.now())
            taskExecutionTimes.computeIfAbsent(task.path) { mutableListOf() }
                .add(duration)
        }
    }
})

// ============================================================================
// Build Time Report Tasks
// ============================================================================

val buildTimeReport by tasks.registering {
    group = "help"
    description = "Report build time statistics for the current build"

    doLast {
        val totalBuildTime = Duration.between(buildStartTime, Instant.now())
        val slowTasks = taskExecutionTimes
            .mapValues { (_, durations) -> durations.sumOf { it.toMillis() } }
            .filter { (_, totalMs) -> totalMs > 100 }
            .entries
            .sortedByDescending { it.value }
            .take(20)

        logger.lifecycle("\n" + "=".repeat(60))
        logger.lifecycle("⏱️  BUILD TIME REPORT")
        logger.lifecycle("=".repeat(60))
        logger.lifecycle("   Total build time: ${formatDuration(totalBuildTime)}")
        logger.lifecycle("   Total tasks tracked: ${taskExecutionTimes.size}")
        logger.lifecycle("")

        if (slowTasks.isNotEmpty()) {
            logger.lifecycle("   Top 20 slowest tasks:")
            logger.lifecycle("   ${"-".repeat(50)}")
            slowTasks.forEachIndexed { index, (taskPath, totalMs) ->
                val duration = Duration.ofMillis(totalMs)
                logger.lifecycle("   ${index + 1}. ${formatDuration(duration).padStart(8)} - $taskPath")
            }
        }

        logger.lifecycle("=".repeat(60))
    }
}

// ============================================================================
// Utility Functions
// ============================================================================

fun formatDuration(duration: Duration): String {
    val hours = duration.toHours()
    val minutes = duration.toMinutes() % 60
    val seconds = duration.seconds % 60
    val millis = duration.toMillis() % 1000

    return when {
        hours > 0 -> "${hours}h ${minutes}m ${seconds}s"
        minutes > 0 -> "${minutes}m ${seconds}s"
        seconds > 0 -> "${seconds}.${millis / 100}s"
        else -> "${millis}ms"
    }
}

group = "com.custom-plugins.build-time-tracker"
version = "1.0.0"