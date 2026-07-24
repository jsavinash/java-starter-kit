// ============================================================================
// Dokka Plugin - Generates API documentation using JetBrains Dokka
// ============================================================================

plugins {
    id("org.jetbrains.dokka")
}

// ============================================================================
// Convenience tasks for documentation generation
// ============================================================================

tasks.register("generateDocs") {
    group = "documentation"
    description = "Generate API documentation using Dokka"
    dependsOn("dokkaHtml")
    doLast {
        logger.lifecycle("✅ Documentation generated")
    }
}

tasks.register("generateAllDocs") {
    group = "documentation"
    description = "Generate all documentation formats"
    dependsOn("dokkaHtml", "generateDocs")
    doLast {
        logger.lifecycle("✅ All documentation generated")
    }
}

group = "com.custom-plugins.dokka"
version = "1.0.0"