plugins {
    `kotlin-dsl` // <1>
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.springboot.gradle.plugin)  // <4>
    compileOnly(libs.spotless.plugin)
    compileOnly(libs.detekt.gradle.plugin)
}
