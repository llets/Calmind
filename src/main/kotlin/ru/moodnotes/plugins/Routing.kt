package ru.moodnotes.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

@kotlinx.serialization.Serializable
data class Test(
    val text: String
)

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello, World!")
        }
    }
}
