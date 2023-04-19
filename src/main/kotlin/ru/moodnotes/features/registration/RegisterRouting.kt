package ru.moodnotes.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import ru.moodnotes.features.registration.RegisterController

fun Application.configureRegisterRouting() {
    routing {
        post("/register") {
            val registerController = RegisterController(call)
            registerController.registerNewUser()
        }
    }
}

