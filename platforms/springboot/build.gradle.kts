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
        api(libs.findLibrary("springboot-dependencies").get())
        api(libs.findLibrary("springboot-starter-data-jpa").get())
        api(libs.findLibrary("springboot-starter-web").get())
        api(libs.findLibrary("postgresql").get())
        api(libs.findLibrary("flyway-core").get())
        api(libs.findLibrary("flyway-database-postgresql").get())
        api(libs.findLibrary("springdoc-openapi-starter-webmvc-ui").get())
    }
}

publishing {
    publications {
        create<MavenPublication>("springbootPlatform") {
            from(components["javaPlatform"])
            artifactId = "springboot-platform"
        }
    }
}