plugins {
    id("com.convention-plugins.springboot-app")
}

group = "com.ecommerce.microservices"
version = "0.0.1"

dependencies {
}

springBoot {
    // Use the set() method with the modern property name
    mainClass.set("com.starter.services.api.gateway.ApiGatewayApplication")
}
