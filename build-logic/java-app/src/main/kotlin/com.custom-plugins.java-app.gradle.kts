plugins {
    id("com.custom-plugins.combined")
    id("application")
}

// Java Application configuration
group = "com.starter.apps"

// Standard test dependencies
dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.12.2")
    testImplementation("org.junit.platform:junit-platform-launcher:6.0.2")
    testImplementation("org.mockito:mockito-core:5.11.0")
    testImplementation("org.assertj:assertj-core:3.26.3")
}
