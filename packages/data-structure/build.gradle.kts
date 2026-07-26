plugins {
    id("com.convention-plugins.java-base")
    id("application")
    id("com.convention-plugins.code-lombok")
    id("com.convention-plugins.junit-platform")

}

group = "com.starter.packages"
version = "0.0.1"

application {
    mainClass.set("data.structure.Application")
}
