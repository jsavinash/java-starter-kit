plugins {
    java
    kotlin("jvm")
    id("checkstyle")
    id("com.custom-plugins.jacoco")
    id("com.custom-plugins.code-formatter")
    id("com.custom-plugins.detekt")
}

kotlin {
    jvmToolchain(25)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

checkstyle {
    toolVersion = "10.25.0"
    var currentDir: java.io.File? = rootProject.rootDir
    while (currentDir != null && !currentDir.resolve("config/checkstyle/checkstyle.xml").exists()) {
        currentDir = currentDir.parentFile
    }
    configFile = currentDir?.resolve("config/checkstyle/checkstyle.xml") 
        ?: error("Could not find checkstyle.xml in any parent directory")
    maxErrors = 0
    maxWarnings = 0
    isIgnoreFailures = false
}

group = "com.custom-plugins.combined"
version = "1.0.0"