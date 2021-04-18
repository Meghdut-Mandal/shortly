package shortner.io

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import shortner.io.plugins.*

fun main() {
    embeddedServer(Netty, port = 8086) {
        configureRouting()
        configureMonitoring()
    }.start(wait = true)
}
