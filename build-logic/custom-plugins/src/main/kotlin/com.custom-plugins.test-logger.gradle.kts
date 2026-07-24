// ============================================================================
// Test Logger Plugin - Enhanced test output with beautiful console logging
// Provides readable, colorized test results for better developer experience.
// ============================================================================

plugins {
    id("com.adarshr.test-logger")
}

// ============================================================================
// Test Configuration - Enforce test execution and logging
// ============================================================================

// Ensure test logger works with JUnit Platform
tasks.withType<Test>().configureEach {
    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed")
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        showExceptions = true
        showCauses = true
        showStackTraces = true
        showStandardStreams = false
    }
}

// ============================================================================
// Test Report Aggregation Tasks
// ============================================================================

val testReportSummary = tasks.register("testReportSummary") {
    group = "verification"
    description = "Generate a summary of all test results"

    dependsOn(tasks.withType<Test>())

    doLast {
        val testResults = mutableMapOf<String, Triple<Int, Int, Int>>()
        var totalPassed = 0
        var totalFailed = 0
        var totalSkipped = 0

        project.subprojects.forEach { subproject ->
            val testTask = subproject.tasks.findByName("test")
            if (testTask is Test) {
                val results = subproject.layout.buildDirectory
                    .dir("test-results/test")
                    .get().asFile

                if (results.exists()) {
                    val xmlFiles = results.listFiles { f -> f.name.endsWith(".xml") } ?: emptyArray()
                    var passed = 0
                    var failed = 0
                    var skipped = 0

                    xmlFiles.forEach { xml ->
                        val content = xml.readText()
                        val testsMatch = Regex("""tests="(\d+)"""").find(content)
                        val failuresMatch = Regex("""failures="(\d+)"""").find(content)
                        val skippedMatch = Regex("""skipped="(\d+)"""").find(content)

                        val tests = testsMatch?.groupValues?.get(1)?.toIntOrNull() ?: 0
                        val failures = failuresMatch?.groupValues?.get(1)?.toIntOrNull() ?: 0
                        val skips = skippedMatch?.groupValues?.get(1)?.toIntOrNull() ?: 0

                        passed += tests - failures - skips
                        failed += failures
                        skipped += skips
                    }

                    testResults[subproject.name] = Triple(passed, failed, skipped)
                    totalPassed += passed
                    totalFailed += failed
                    totalSkipped += skipped
                }
            }
        }

        logger.lifecycle("\n" + "=".repeat(60))
        logger.lifecycle("📊 TEST REPORT SUMMARY")
        logger.lifecycle("=".repeat(60))

        testResults.forEach { (name, result) ->
            val (passed, failed, skipped) = result
            val status = when {
                failed > 0 -> "❌ FAILED"
                skipped > 0 -> "⚠️  PASSED (with skips)"
                else -> "✅ PASSED"
            }
            logger.lifecycle("   $status $name: $passed passed, $failed failed, $skipped skipped")
        }

        logger.lifecycle("-".repeat(60))
        logger.lifecycle("   Total: $totalPassed passed, $totalFailed failed, $totalSkipped skipped")
        logger.lifecycle("=".repeat(60))

        if (totalFailed > 0) {
            throw GradleException("Test failures detected: $totalFailed test(s) failed")
        }
    }
}

group = "com.custom-plugins.test-logger"
version = "1.0.0"