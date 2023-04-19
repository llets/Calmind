package ru.moodnotes.plugins

import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import ru.moodnotes.cache.InMemoryCache
import ru.moodnotes.cache.TokenCache
import ru.moodnotes.features.login.LoginController
import ru.moodnotes.features.login.LoginReceiveRemote
import ru.moodnotes.features.login.LoginResponseRemote
import java.util.*

fun Application.configureLoginRouting() {
    routing {
        post("/login") {
            val loginController = LoginController(call)
            loginController.performLogin()
        }
    }
}
