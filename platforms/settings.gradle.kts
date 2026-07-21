// ============================================================================
// Platforms - BOM (Bill of Materials) Composite Build
// Provides curated dependency version catalogs for different technology stacks.
// ============================================================================

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "platforms"

include("springboot")
include("test")
include("web")
include("android")