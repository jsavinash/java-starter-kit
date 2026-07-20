// == Define locations for build logic ==
pluginManagement {
    repositories {
        gradlePluginPortal() // if pluginManagement.repositories looks like this, it can be omitted as this is the default
    }
    includeBuild("../../build-logic/springboot-app")
}

// == Define locations for components ==
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
include("../../platforms/springboot")

// == Define the inner structure of this component ==
rootProject.name = "shared"
include("configurations")
include("constants")
include("entities")
include("enums")
include("utility")
