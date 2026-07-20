plugins {
    id("com.starter.commons")
    id("application")
}

group = "com.starter.builder"
version = "0.0.1"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

repositories {
    mavenCentral()
}

dependencies {
}
