plugins {
    `java-platform`
    `maven-publish`
}

javaPlatform {
    allowDependencies()
}

val libs = versionCatalogs.named("libs")

dependencies {
    constraints {
        api(libs.findLibrary("junit-bom").get())
    }
}

publishing {
    publications {
        create<MavenPublication>("testPlatform") {
            from(components["javaPlatform"])
            artifactId = "test-platform"
        }
    }
}