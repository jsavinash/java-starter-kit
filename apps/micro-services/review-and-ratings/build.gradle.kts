plugins {
    id("com.starter.spring-boot-application")
}

group = "com.starter.services"
version = "0.0.1"

dependencies {
    implementation(libs.springboot.starter.web)
    implementation("com.starter.shared:utility")
}

springBoot {
    // Use the set() method with the modern property name
    mainClass.set("com.starter.services.review.ReviewAndRatingsApplication")
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    this.archiveFileName.set("${project.name}-${project.version}-build.jar")
}
