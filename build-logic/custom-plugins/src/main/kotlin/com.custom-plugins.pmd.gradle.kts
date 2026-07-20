plugins {
    id("pmd")
}

pmd {
    toolVersion = "7.12.0"
    isIgnoreFailures = false
    
    // Use default rulesets since custom ruleset file path is problematic in composite builds
    ruleSets = listOf("category/java/bestpractices.xml", "category/java/errorprone.xml")
}

group = "com.custom-plugins.pmd"
version = "1.0.0"
