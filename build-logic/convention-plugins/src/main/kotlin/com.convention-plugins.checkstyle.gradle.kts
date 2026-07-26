plugins {
    id("checkstyle")
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
