pluginManagement {
    repositories {
        gradlePluginPortal()
    }
    includeBuild("../../build-logic")
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

rootProject.name = "system-design"

include("patterns:abstract-document")
include("patterns:abstract-factory")
include("patterns:active-object")
include("patterns:actor-model")
include("patterns:acyclic-visitor")
include("patterns:adapter")
include("patterns:ambassador")
include("patterns:anti-corruption-layer")
include("patterns:arrange-act-assert")
include("patterns:async-method-invocation")
include("patterns:backpressure")
include("patterns:balking")
include("patterns:bloc")
include("patterns:bridge")
include("patterns:builder")
include("patterns:business-delegate")
include("patterns:bytecode")
//include("patterns:caching")
//include("patterns:callback")
//include("patterns:chain-of-responsibility")
//include("patterns:circuit-breaker")

