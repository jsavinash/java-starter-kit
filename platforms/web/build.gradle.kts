plugins {
    `java-platform`
    `maven-publish`
}

group = "com.starter.platforms"
version = "1.0.0"

javaPlatform {
    allowDependencies()
}

val libs = versionCatalogs.named("libs")

dependencies {
    constraints {
        api(libs.findLibrary("ktor-client-core").get())
        api(libs.findLibrary("ktor-client-cio").get())
        api(libs.findLibrary("ktor-client-content-negotiation").get())
        api(libs.findLibrary("ktor-serialization-kotlinx-json").get())
        api(libs.findLibrary("kotlinx-serialization-json").get())
    }
}

publishing {
    publications {
        create<MavenPublication>("webPlatform") {
            from(components["javaPlatform"])
            artifactId = "web-platform"
        }
    }
}