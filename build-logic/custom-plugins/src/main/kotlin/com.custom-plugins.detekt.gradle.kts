plugins {
    id("io.gitlab.arturbosch.detekt")
}

detekt {
    buildUponDefaultConfig = true
    allRules = false

    // Dynamically locate detekt.yml by walking up parent directories
    var currentDir: java.io.File? = rootProject.rootDir
    while (currentDir != null && !currentDir.resolve("config/detekt/detekt.yml").exists()) {
        currentDir = currentDir.parentFile
    }
    if (currentDir != null) {
        config.setFrom(currentDir.resolve("config/detekt/detekt.yml"))
    }
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        html.required.set(true)
        xml.required.set(false)
        txt.required.set(false)
        sarif.required.set(false)
    }
}

group = "com.custom-plugins.detekt"
version = "1.0.0"
