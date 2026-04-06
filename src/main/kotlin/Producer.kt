import com.rabbitmq.client.ConnectionFactory

fun main() {
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
}
