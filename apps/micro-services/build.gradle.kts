// Root-level aggregation tasks for the micro-services composite build.
// Spotless is applied per-subproject via the convention plugin chain
// (com.custom-plugins.springboot-app → com.starter.commons → com.starter.code-formatter).
// These tasks aggregate the subproject-level tasks so they can be invoked from the root.

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
