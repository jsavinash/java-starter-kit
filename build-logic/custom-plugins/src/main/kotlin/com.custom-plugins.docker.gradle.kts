// ============================================================================
// Docker Plugin - Containerization support using bmuschko/gradle-docker-plugin
// Provides tasks for building, pushing, and managing Docker images.
// ============================================================================

plugins {
    id("com.bmuschko.docker-remote-api")
}

import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage
import com.bmuschko.gradle.docker.tasks.image.DockerPushImage
import com.bmuschko.gradle.docker.tasks.image.Dockerfile
import com.bmuschko.gradle.docker.tasks.container.DockerCreateContainer
import com.bmuschko.gradle.docker.tasks.container.DockerStartContainer
import com.bmuschko.gradle.docker.tasks.container.DockerStopContainer
import com.bmuschko.gradle.docker.tasks.container.DockerRemoveContainer
import com.bmuschko.gradle.docker.tasks.image.DockerRemoveImage
import com.bmuschko.gradle.docker.tasks.image.DockerTagImage

// ============================================================================
// Docker Configuration
// ============================================================================

// Determine Docker image name from project
val dockerImageName = project.name.lowercase().replace(Regex("[^a-z0-9-]"), "-")
val dockerTag = project.version.takeIf { it.toString() != "unspecified" }?.toString() ?: "latest"
val dockerRegistry = System.getenv("DOCKER_REGISTRY") ?: ""

// ============================================================================
// Dockerfile Generation
// ============================================================================

val dockerCreateDockerfile by tasks.registering(Dockerfile::class) {
    group = "docker"
    description = "Create Dockerfile for the project"

    // Base image - use a slim Java image
    from("eclipse-temurin:21-jre-alpine")

    // Metadata labels
    label(mapOf(
        "maintainer" to "Java Starter Kit",
        "project" to project.name,
        "version" to project.version.toString(),
        "description" to (project.description ?: "Java Starter Kit Microservice")
    ))

    // Default port exposed by the application
    exposePort(8080)

    // Health check
    val healthCheckCommand = listOf("wget", "--quiet", "--tries=1", "--spider", "http://localhost:8080/actuator/health")
    instruction("HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 CMD ${healthCheckCommand.joinToString(" ")}")

    // Copy the application JAR
    val jarFile = project.tasks.withType<Jar>().findByName("bootJar")
        ?: project.tasks.withType<Jar>().findByName("jar")

    if (jarFile != null) {
        copyFile(jarFile.outputs.files.singleFile.name, "/app/app.jar")
    }

    // Set working directory
    workingDir("/app")

    // Run as non-root user
    instruction("RUN addgroup -S appgroup && adduser -S appuser -G appgroup")
    instruction("USER appuser")

    // Entry point
    entryPoint("java", "-jar", "/app/app.jar")

    // JVM options
    defaultCommand(
        "-XX:+UseContainerSupport",
        "-XX:MaxRAMPercentage=75.0",
        "-XX:+ExitOnOutOfMemoryError",
        "-jar", "/app/app.jar"
    )

    // Set the destination for the Dockerfile
    destFile = project.layout.buildDirectory.dir("docker").get().file("Dockerfile").asFile
}

// ============================================================================
// Docker Build Task
// ============================================================================

val dockerBuildImage by tasks.registering(DockerBuildImage::class) {
    group = "docker"
    description = "Build Docker image for the project"
    dependsOn(dockerCreateDockerfile)

    // Use the generated Dockerfile
    dockerFile.set(dockerCreateDockerfile.get().destFile)

    // Image name and tag
    images.add("$dockerRegistry$dockerImageName:$dockerTag")
    images.add("$dockerRegistry$dockerImageName:latest")

    // Build arguments
    buildArgs.set(mapOf(
        "JAR_FILE" to project.tasks.withType<Jar>().findByName("bootJar")?.outputs?.files?.singleFile?.name
            ?: project.tasks.withType<Jar>().findByName("jar")?.outputs?.files?.singleFile?.name
            ?: "app.jar"
    ))

    // Squash for smaller layers
    squash.set(false)

    // Label the image
    labels.set(mapOf(
        "org.opencontainers.image.title" to project.name,
        "org.opencontainers.image.version" to project.version.toString(),
        "org.opencontainers.image.created" to java.time.Instant.now().toString(),
        "org.opencontainers.image.source" to "https://github.com/jsavinash/java-starter-kit"
    ))
}

// ============================================================================
// Docker Tag Task
// ============================================================================

val dockerTagImage by tasks.registering(DockerTagImage::class) {
    group = "docker"
    description = "Tag Docker image with additional tags"
    dependsOn(dockerBuildImage)

    imageId.set("$dockerRegistry$dockerImageName:$dockerTag")
    tag.set("$dockerRegistry$dockerImageName:${System.getenv("BUILD_NUMBER") ?: "latest"}")
    force.set(true)
}

// ============================================================================
// Docker Push Task
// ============================================================================

val dockerPushImage by tasks.registering(DockerPushImage::class) {
    group = "docker"
    description = "Push Docker image to registry"
    dependsOn(dockerTagImage)

    imageName.set(dockerImageName)

    // Use registry credentials from environment
    if (dockerRegistry.isNotEmpty()) {
        registryCredentials {
            username.set(System.getenv("DOCKER_USERNAME") ?: "")
            password.set(System.getenv("DOCKER_PASSWORD") ?: "")
        }
    }
}

// ============================================================================
// Container Management Tasks
// ============================================================================

val dockerCreateContainer by tasks.registering(DockerCreateContainer::class) {
    group = "docker"
    description = "Create Docker container from the built image"
    dependsOn(dockerBuildImage)

    targetImageId { dockerBuildImage.get().imageId.get() }
    containerName.set("${dockerImageName}-${System.currentTimeMillis()}")

    hostConfig.portBindings.set(listOf("8080:8080"))
    hostConfig.autoRemove.set(true)
}

val dockerStartContainer by tasks.registering(DockerStartContainer::class) {
    group = "docker"
    description = "Start Docker container"
    dependsOn(dockerCreateContainer)

    targetContainerId { dockerCreateContainer.get().containerId }
}

val dockerStopContainer by tasks.registering(DockerStopContainer::class) {
    group = "docker"
    description = "Stop Docker container"
}

val dockerRemoveContainer by tasks.registering(DockerRemoveContainer::class) {
    group = "docker"
    description = "Remove Docker container"
    dependsOn(dockerStopContainer)
}

val dockerRemoveImage by tasks.registering(DockerRemoveImage::class) {
    group = "docker"
    description = "Remove Docker image"
    dependsOn(dockerBuildImage)

    imageId.set("$dockerRegistry$dockerImageName:$dockerTag")
    force.set(true)
}

// ============================================================================
// Convenience tasks
// ============================================================================

// Build and push all at once
val dockerPublish by tasks.registering {
    group = "docker"
    description = "Build, tag, and push Docker image to registry"
    dependsOn(dockerPushImage)

    doLast {
        logger.lifecycle("✅ Docker image published: $dockerRegistry$dockerImageName:$dockerTag")
    }
}

// Full Docker lifecycle
val dockerLifecycle by tasks.registering {
    group = "docker"
    description = "Build, run, and manage Docker container lifecycle"
    dependsOn(dockerStartContainer)

    doLast {
        logger.lifecycle("✅ Docker container started: ${dockerImageName}-latest")
        logger.lifecycle("   Access the application at: http://localhost:8080")
    }
}

group = "com.custom-plugins.docker"
version = "1.0.0"