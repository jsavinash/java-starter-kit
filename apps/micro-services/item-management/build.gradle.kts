plugins {
    id("com.convention-plugins.springboot-app")
}

group = "com.starter.services"
version = "0.0.1"

springBoot {
    // Use the set() method with the modern property name
    mainClass.set("com.starter.services.item.management.ItemManagementApplication")
}
