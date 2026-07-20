// ============================================================================
// Root-level aggregation tasks for micro-services composite build
// ============================================================================

// === Aggregation Tasks ===

tasks.register("spotlessApply") {
    group = "formatting"
    description = "Runs spotlessApply across all micro-service subprojects"
    dependsOn(subprojects.mapNotNull { it.tasks.findByName("spotlessApply") })
}

tasks.register("spotlessCheck") {
    group = "formatting"
    description = "Runs spotlessCheck across all micro-service subprojects"
    dependsOn(subprojects.mapNotNull { it.tasks.findByName("spotlessCheck") })
}

tasks.register("testAll") {
    group = "verification"
    description = "Run all tests across all micro-services"
    dependsOn(subprojects.mapNotNull { it.tasks.findByName("test") })
}

tasks.register("checkstyleAll") {
    group = "verification"
    description = "Run checkstyle across all micro-services"
    dependsOn(subprojects.mapNotNull { it.tasks.findByName("checkstyleMain") })
}

tasks.register("detektAll") {
    group = "verification"
    description = "Run detekt across all micro-services"
    dependsOn(subprojects.mapNotNull { it.tasks.findByName("detektMain") })
}

tasks.register("pmdAll") {
    group = "verification"
    description = "Run PMD across all micro-services"
    dependsOn(subprojects.mapNotNull { it.tasks.findByName("pmdMain") })
}

tasks.register("qualityGate") {
    group = "verification"
    description = "Run all quality checks across all micro-services"
    dependsOn(
        tasks.named("spotlessCheck"),
        tasks.named("checkstyleAll"),
        tasks.named("detektAll"),
        tasks.named("pmdAll"),
        tasks.named("testAll")
    )
    doLast {
        logger.lifecycle("✅ Micro-services quality gate passed")
    }
}

tasks.register("buildAll") {
    group = "build"
    description = "Build all micro-services"
    dependsOn(tasks.named("qualityGate"))
    dependsOn(subprojects.mapNotNull { it.tasks.findByName("build") })
}

// === Reporting ===
tasks.register("listServices") {
    group = "help"
    description = "List all micro-services in the project"
    doLast {
        logger.lifecycle("\n📋 Micro-services (${subprojects.size}):")
        subprojects.sortedBy { it.name }.forEach { service ->
            logger.lifecycle("  ${service.name}")
        }
    }
}

// === Dependency Management ===
tasks.register("checkDependencies") {
    group = "help"
    description = "Check for available dependency updates"
    doLast {
        logger.lifecycle("\nRun './gradlew dependencyCheckAnalyze' for OWASP vulnerability scan")
    }
}
