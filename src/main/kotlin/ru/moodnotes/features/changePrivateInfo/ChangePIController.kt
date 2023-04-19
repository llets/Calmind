package ru.moodnotes.features.changePrivateInfo

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.moodnotes.database.tokens.TokenDTO
import ru.moodnotes.database.tokens.Tokens
import ru.moodnotes.database.users.UserDTO
import ru.moodnotes.database.users.Users
import ru.moodnotes.features.login.LoginReceiveRemote
import ru.moodnotes.features.login.LoginResponseRemote
import java.util.*

class ChangePIController(private val call: ApplicationCall) {

    suspend fun changePassword(){
        val changePIReceiveRemote = call.receive<ChangePIReceiveRemote>()
        val userDTO = Users.fetchUser(changePIReceiveRemote.email)

        println("receive -> $changePIReceiveRemote, dto -> $userDTO")

        if (userDTO == null){
            call.respond(HttpStatusCode.BadRequest, "User not found")
        } else {
            if (userDTO.password == changePIReceiveRemote.oldPassword) {
                Users.updatePassword(
                    UserDTO(
                        email = "",
                        username = "",
                        password = changePIReceiveRemote.newPassword
                    )
                )
                call.respond(ChangePIRespondRemote(isChanged = "Yes"))
            } else{
                call.respond(HttpStatusCode.BadRequest, "Old password is incorrect")
            }
        }
    }
}