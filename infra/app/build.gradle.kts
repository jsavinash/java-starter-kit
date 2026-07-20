plugins { id("com.custom-plugins.springboot-app") }
group = "com.starter.infra"; version = "0.0.1"
repositories { mavenCentral() }

val assetsDir = rootProject.projectDir.resolve("assets/deployment")

tasks.register<Sync>("syncAssets") {
    from(assetsDir) { include("**/*.yml","**/*.yaml","**/*.tf","**/*.tfvars","**/*.sh","**/*.sql","**/.env") }
    into(layout.buildDirectory.dir("deployment"))
    inputs.dir(assetsDir)
}
tasks.named("bootRun") { dependsOn("syncAssets") }
tasks.named("processResources") { dependsOn("syncAssets") }

dependencies {
    implementation(libs.springboot.starter.web)
    implementation(libs.springdoc.openapi.starter.webmvc.ui)
    implementation("com.starter.shared:utility")

    implementation("com.github.docker-java:docker-java:3.4.0")
    implementation("com.github.docker-java:docker-java-transport-httpclient5:3.4.0")

    implementation("io.kubernetes:client-java-extended:21.0.2")
    implementation("io.kubernetes:client-java-spring-integration:21.0.2")

    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.19.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.19.1")

    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

springBoot { mainClass.set("com.starter.infra.InfraApp") }