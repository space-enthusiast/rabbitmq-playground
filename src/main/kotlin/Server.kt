import com.rabbitmq.client.ConnectionFactory
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.html.*
import io.ktor.server.netty.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*

private val factory = ConnectionFactory().apply { host = "localhost" }
private val connection = factory.newConnection()

fun main() {
    embeddedServer(Netty, port = 8080) {
        routing {
            get("/") {
                call.respondHtml {
                    head { title("RabbitMQ Producer") }
                    body {
                        h2 { +"RabbitMQ Producer" }
                        form(action = "/send", method = FormMethod.post) {
                            input(type = InputType.text, name = "message") {
                                placeholder = "메시지 입력"
                                size = "40"
                            }
                            +" "
                            button(type = ButtonType.submit) { +"Send" }
                        }
                    }
                }
            }

            post("/send") {
                val message = call.receiveParameters()["message"] ?: ""
                println("[/send] message='$message'")
                if (message.isNotBlank()) {
                    try {
                        val channel = connection.createChannel()
                        channel.use {
                            it.queueDeclare("hello", false, false, false, null)
                            it.basicPublish("", "hello", null, message.toByteArray())
                        }
                        println("[/send] published ok")
                    } catch (e: Exception) {
                        println("[/send] error: ${e.message}")
                    }
                } else {
                    println("[/send] message is blank, skipped")
                }
                call.respondRedirect("/")
            }
        }
    }.start(wait = true)
}
