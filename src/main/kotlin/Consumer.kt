import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback

fun main() {
    val queueName = "hello"

    val factory = ConnectionFactory().apply {
        host = "localhost"
    }

    val connection = factory.newConnection()
    val channel = connection.createChannel()

    channel.queueDeclare(queueName, false, false, false, null)
    println(" [*] Waiting for messages. Press Ctrl+C to exit.")

    val deliverCallback = DeliverCallback { _, delivery ->
        val message = String(delivery.body)
        println(" [x] Received '$message'")
    }

    val cancelCallback = CancelCallback { consumerTag ->
        println(" [x] Consumer '$consumerTag' cancelled")
    }

    channel.basicConsume(queueName, true, deliverCallback, cancelCallback)
}
