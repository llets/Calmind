package ru.moodnotes.features.changePrivateInfo

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureChangePIRouting() {
    routing {
        post("/changePassword") {
            val changePIController = ChangePIController(call)
            changePIController.changePassword()
        }
        post("/changeUsername") {
            val changePIController = ChangePIController(call)
            changePIController.changeUsername()
        }
//        post("/changeEmail") {
//            val changePIController = ChangePIController(call)
//            changePIController.changeEmail()
//        }
    }

}