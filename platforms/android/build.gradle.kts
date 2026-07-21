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
        api(libs.findLibrary("androidx-core-ktx").get())
        api(libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
        api(libs.findLibrary("androidx-activity-compose").get())
        api(libs.findLibrary("compose-bom").get())
    }
}

publishing {
    publications {
        create<MavenPublication>("androidPlatform") {
            from(components["javaPlatform"])
            artifactId = "android-platform"
        }
    }
}