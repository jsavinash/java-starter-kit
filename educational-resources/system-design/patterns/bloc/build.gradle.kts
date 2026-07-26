plugins {
    id("com.convention-plugins.java-app")
}

group = "com.iluwatar"
version = "1.0.0"

application {
    mainClass.set("com.iluwatar.bloc.Main")
}

dependencies {
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.jacocoTestCoverageVerification {
    violationRules.rules.forEach { rule ->
        rule.excludes = rule.excludes + listOf(
            "com.iluwatar.bloc.BlocUi*",
            "com.iluwatar.bloc.Main*"
        )
    }
    violationRules.rules.first().limits.forEach { limit ->
        when (limit.counter) {
            "INSTRUCTION" -> limit.minimum = "0.30".toBigDecimal()
            "LINE" -> limit.minimum = "0.30".toBigDecimal()
            "BRANCH" -> limit.minimum = "0.40".toBigDecimal()
            "METHOD" -> limit.minimum = "0.40".toBigDecimal()
            "CLASS" -> limit.minimum = "0.50".toBigDecimal()
        }
    }
}