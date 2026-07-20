pluginManagement {
    repositories { gradlePluginPortal() }
    includeBuild("../build-logic")
}
dependencyResolutionManagement {
    repositories { mavenCentral() }
    versionCatalogs { create("libs") { from(files("../gradle/libs.versions.toml")) } }
}
includeBuild("../shared")
rootProject.name = "infra"
include("app")
include("assets")