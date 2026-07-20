plugins {
    id("java")
    id("jacoco")
}

// Do not generate reports for individual projects (aggregate reports are used)
tasks.jacocoTestReport.configure {
    enabled = false
}

// JaCoCo coverage rules with thresholds
tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.80".toBigDecimal() // 80% minimum instruction coverage
                counter = "INSTRUCTION"
                value = "COVEREDRATIO"
            }
            limit {
                minimum = "0.80".toBigDecimal() // 80% minimum line coverage
                counter = "LINE"
                value = "COVEREDRATIO"
            }
            limit {
                minimum = "0.60".toBigDecimal() // 60% minimum branch coverage
                counter = "BRANCH"
                value = "COVEREDRATIO"
            }
            limit {
                minimum = "0.70".toBigDecimal() // 70% minimum method coverage
                counter = "METHOD"
                value = "COVEREDRATIO"
            }
            limit {
                minimum = "0.80".toBigDecimal() // 80% minimum class coverage
                counter = "CLASS"
                value = "COVEREDRATIO"
            }
        }

        // Exclude certain classes from coverage requirements
        rule {
            element = "CLASS"
            excludes = listOf(
                "**.*Application*",
                "**.*Configuration*",
                "**.*Config*",
                "**.*Dto*",
                "**.*DTO*",
                "**.*Request*",
                "**.*Response*",
                "**.*Exception*",
                "**.*Error*",
                "**.*Constants*",
                "**.*Entity*",
                "**.*Model*",
                "**.*Builder*"
            )
            limit {
                minimum = "0.80".toBigDecimal()
                counter = "LINE"
                value = "COVEREDRATIO"
            }
        }
    }
}

// Ensure coverage verification runs after tests
tasks.test {
    finalizedBy(tasks.jacocoTestReport, tasks.jacocoTestCoverageVerification)
}

// Fail the build on coverage failure
tasks.jacocoTestCoverageVerification {
    dependsOn(tasks.test)
}

group = "com.custom-plugins.jacoco"
version = "1.0.0"
