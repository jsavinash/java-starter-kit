plugins {
    id("java")
    id("jacoco")
}

// Do not generate reports for individual projects
tasks.jacocoTestReport.configure {
    enabled = false
}

group = "com.custom-plugins.jacoco"
version = "1.0.0"
