plugins {
    kotlin("jvm") version "2.1.20"
    application
}

repositories {
    mavenCentral()
}

val ktorVersion = "2.3.12"

dependencies {
    implementation("com.rabbitmq:amqp-client:5.24.0")
    implementation("org.slf4j:slf4j-simple:2.0.16")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-html-builder:$ktorVersion")
}

application {
    mainClass = "ServerKt"
}

tasks.register<JavaExec>("runConsumer") {
    classpath = sourceSets["main"].runtimeClasspath
    mainClass = "ConsumerKt"
    dependsOn("classes")
}

tasks.register<JavaExec>("runProducer") {
    classpath = sourceSets["main"].runtimeClasspath
    mainClass = "ProducerKt"
    dependsOn("classes")
}
