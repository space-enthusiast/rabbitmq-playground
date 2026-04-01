@file:Repository("https://repo.maven.apache.org/maven2")
@file:DependsOn("com.rabbitmq:amqp-client:5.24.0")
@file:DependsOn("org.slf4j:slf4j-simple:2.0.16")

import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback

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
