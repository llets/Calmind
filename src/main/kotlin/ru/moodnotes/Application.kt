package ru.moodnotes

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import org.jetbrains.exposed.sql.Database
import ru.moodnotes.plugins.*

fun main() {
    Database.connect("jdbc:postgresql://localhost:5432/moodnotes", user="postgres", driver="org.postgresql.Driver", password = "root")
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureLoginRouting()
    configureRegisterRouting()
    configureSerialization()
}
