pluginManagement {
    repositories {
        gradlePluginPortal()
    }
    include("../../build-logic/springboot-app")
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files("../../gradle/libs.versions.toml"))
        }
    }
}

include("../../platforms/springboot")
includeBuild("../../shared")

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
