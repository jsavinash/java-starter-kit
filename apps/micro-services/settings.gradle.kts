pluginManagement {
    repositories {
        gradlePluginPortal()
    }
    includeBuild("../../build-logic")
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        mavenLocal()
    }
    versionCatalogs {
        create("libs") {
            from(files("../../gradle/libs.versions.toml"))
        }
    }
}

includeBuild("../../shared")

// Platforms BOM composite build - provides curated dependency versions.
// Automatically substitutes com.starter.platforms:* Maven coordinates
// with the corresponding project artifacts from the platforms build.
includeBuild("../../platforms") {
    dependencySubstitution {
        substitute(module("com.starter.platforms:springboot-platform")).using(project(":springboot"))
        substitute(module("com.starter.platforms:test-platform")).using(project(":test"))
        substitute(module("com.starter.platforms:web-platform")).using(project(":web"))
        substitute(module("com.starter.platforms:android-platform")).using(project(":android"))
    }
}

rootProject.name = "micro-services"

// Include all active services
include("api-gateway")
include("config-server")
include("service-discovery")
include("user")
include("item")
include("item-management")
include("product")
include("review-and-ratings")
include("inventory")
include("recommendations")
include("offers")
include("cart")
include("order")
include("archival")
include("notification")
include("serviceability")
include("payment")