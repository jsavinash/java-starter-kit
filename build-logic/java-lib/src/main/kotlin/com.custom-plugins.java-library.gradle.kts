plugins {
    id("com.custom-plugins.combined")
    id("java-library")
}

// Java Library specific configuration
group = "com.starter.shared"

// API/Implementation separation for better dependency management
configurations {
    api {
        outgoing {
            capability("${project.group}:${project.name}:${project.version}")
        }
    }
}

// Test dependencies - using string notation for build-logic
dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.12.2")
    testImplementation("org.junit.platform:junit-platform-launcher:6.0.2")
    testImplementation("org.mockito:mockito-core:5.17.0")
    testImplementation("org.assertj:assertj-core:5.17.0")
}