package ru.moodnotes.features.changePrivateInfo

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.moodnotes.features.login.LoginController

fun Application.configureChangePIRouting() {
    routing {
        post("/changePassword") {
            val loginController = LoginController(call)
            loginController.performLogin()
        }
        post("/changeUsername") {
            val loginController = LoginController(call)
            loginController.performLogin()
        }
        post("/changeEmail") {
            val loginController = LoginController(call)
            loginController.performLogin()
        }
    }

}