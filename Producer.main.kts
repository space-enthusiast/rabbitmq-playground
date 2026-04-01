@file:Repository("https://repo.maven.apache.org/maven2")
@file:DependsOn("com.rabbitmq:amqp-client:5.24.0")
@file:DependsOn("org.slf4j:slf4j-simple:2.0.16")

import com.rabbitmq.client.ConnectionFactory

val queueName = "hello"

val factory = ConnectionFactory().apply {
    host = "localhost"
}

factory.newConnection().use { connection ->
    connection.createChannel().use { channel ->
        channel.queueDeclare(queueName, false, false, false, null)
        val message = "Hello, RabbitMQ!"
        channel.basicPublish("", queueName, null, message.toByteArray())
        println(" [x] Sent '$message'")
    }
}
