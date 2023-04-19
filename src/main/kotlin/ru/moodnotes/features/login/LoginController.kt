package ru.moodnotes.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.moodnotes.database.tokens.TokenDTO
import ru.moodnotes.database.tokens.Tokens
import ru.moodnotes.database.users.Users
import java.util.*

class LoginController(private val call: ApplicationCall) {

    suspend fun performLogin(){
        val loginReceiveRemote = call.receive<LoginReceiveRemote>()
        val userDTO = Users.fetchUser(loginReceiveRemote.email)

        println("receive -> $loginReceiveRemote, dto -> $userDTO")

        if (userDTO == null){
            call.respond(HttpStatusCode.BadRequest, "User not found")
        } else {
            if (userDTO.password == loginReceiveRemote.password) {
                val token = UUID.randomUUID().toString()
                val rowId = UUID.randomUUID().toString()
                Tokens.insert(
                    TokenDTO(
                        rowId = rowId,
                        email = loginReceiveRemote.email,
                        token = token)
                )
                call.respond(LoginResponseRemote(token=token))
            } else{
                call.respond(HttpStatusCode.BadRequest, "Invalid password")
            }
        }
    }
}