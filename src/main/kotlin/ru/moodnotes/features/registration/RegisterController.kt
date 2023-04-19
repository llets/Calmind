package ru.moodnotes.features.registration

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import ru.moodnotes.database.tokens.Tokens
import ru.moodnotes.database.tokens.TokenDTO
import ru.moodnotes.database.users.UserDTO
import ru.moodnotes.database.users.Users
import ru.moodnotes.features.utils.isValidEmail
import ru.moodnotes.features.utils.isValidPassword
import java.util.*

class RegisterController(private val call: ApplicationCall) {

    suspend fun registerNewUser(){
        val registerReceiveRemote = call.receive<RegisterReceiveRemote>()
        if (!registerReceiveRemote.email.isValidEmail()){
            call.respond(HttpStatusCode.BadRequest, "Email is not valid")
        }
        val (isValid, message) = registerReceiveRemote.password.isValidPassword()
        if (!isValid){
            call.respond(HttpStatusCode.BadRequest, message)
        } else {
            val userDTO = Users.fetchUser(registerReceiveRemote.email)

            println("receive -> $registerReceiveRemote, dto -> $userDTO")

            if (userDTO != null) {
                call.respond(HttpStatusCode.Conflict, "User already exists")
            } else {
                val token = UUID.randomUUID().toString()
                val rowId = UUID.randomUUID().toString()
                try {
                    Users.insert(
                        UserDTO(
                            password = registerReceiveRemote.password,
                            email = registerReceiveRemote.email,
                            username = ""
                        )
                    )
                } catch (e: ExposedSQLException) {
                    call.respond(HttpStatusCode.Conflict, "User already exists")
                }
                Tokens.insert(
                    TokenDTO(
                        rowId = rowId,
                        email = registerReceiveRemote.email,
                        token = token
                    )
                )
                call.respond(RegisterResponseRemote(token = token))
            }
        }
    }
}