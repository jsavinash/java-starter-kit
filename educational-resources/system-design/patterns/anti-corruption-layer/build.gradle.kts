plugins {
    id("com.convention-plugins.springboot-app")
    alias(libs.plugins.lombok)
}

group = "com.iluwatar"
version = "1.0.0"

lombok {
    version.set(libs.versions.lombokLibrary.get())
}

springBoot {
    mainClass.set("com.iluwatar.corruption.App")
}

tasks.withType<JacocoCoverageVerification>().configureEach {
    violationRules.rules.forEach { rule ->
        rule.excludes = rule.excludes + listOf("com.iluwatar.corruption.App")
    }
}