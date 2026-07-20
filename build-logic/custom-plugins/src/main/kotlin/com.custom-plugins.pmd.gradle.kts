plugins {
    id("pmd")
}

pmd {
    toolVersion = "7.12.0"
    isIgnoreFailures = false
    ruleSetFiles = files(
        rootProject.rootDir.resolve("config/pmd/pmd-ruleset.xml").takeIf { it.exists() }
            ?: rootProject.parent?.rootDir?.resolve("config/pmd/pmd-ruleset.xml")
            ?: error("Could not find pmd-ruleset.xml")
    )
    ruleSets = emptyList() // Disable default rulesets, use our custom one
    consoleOutput = true
    sourceSets = listOf(sourceSets.main.get())
    maxFailures = 0
}

tasks.withType<pmdMain>().configureEach {
    reports {
        html.required.set(true)
        xml.required.set(true)
    }
}

group = "com.custom-plugins.pmd"
version = "1.0.0"