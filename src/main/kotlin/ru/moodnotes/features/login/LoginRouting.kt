package ru.moodnotes.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import ru.moodnotes.features.login.LoginController

fun Application.configureLoginRouting() {
    routing {
        post("/login") {
            val loginController = LoginController(call)
            loginController.performLogin()
        }
    }
}
