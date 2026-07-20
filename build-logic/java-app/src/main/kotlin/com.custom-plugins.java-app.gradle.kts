plugins {
    id("com.custom-plugins.combined")
    id("application")
}

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
