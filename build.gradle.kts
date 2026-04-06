plugins {
    kotlin("jvm") version "2.1.20"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.rabbitmq:amqp-client:5.24.0")
    implementation("org.slf4j:slf4j-simple:2.0.16")
}

application {
    mainClass = "ProducerKt"
}

tasks.register<JavaExec>("runConsumer") {
    classpath = sourceSets["main"].runtimeClasspath
    mainClass = "ConsumerKt"
    dependsOn("classes")
}
