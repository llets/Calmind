package ru.moodnotes.features.changePrivateInfo

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.moodnotes.database.users.UserDTO
import ru.moodnotes.database.users.Users
import ru.moodnotes.features.utils.isValidPassword

class ChangePIController(private val call: ApplicationCall) {

    suspend fun changePassword(){
        val changePasswordReceiveRemote = call.receive<ChangePasswordReceiveRemote>()
        val userDTO = Users.fetchUser(changePasswordReceiveRemote.email)

        println("receive -> $changePasswordReceiveRemote, dto -> $userDTO")

        if (userDTO == null){
            call.respond(HttpStatusCode.BadRequest, "Something went wrong. " +
                    "Current email wasn't found in the database")
        } else {
            if (userDTO.password == changePasswordReceiveRemote.oldPassword) {
                if (userDTO.password == changePasswordReceiveRemote.newPassword) {
                    call.respond(HttpStatusCode.BadRequest, "New password can't be the same as old one. Try again")
                } else {
                    val (isValid, message) = changePasswordReceiveRemote.newPassword.isValidPassword()
                    if (!isValid) {
                        call.respond(HttpStatusCode.BadRequest, message)
                    } else {
                        Users.updatePassword(
                            UserDTO(
                                email = changePasswordReceiveRemote.email,
                                username = userDTO.username,
                                password = changePasswordReceiveRemote.newPassword
                            )
                        )
                        call.respond(HttpStatusCode.OK)
                    }
                }
            } else{
                call.respond(HttpStatusCode.BadRequest, "Old password is incorrect. Try again")
            }
        }
    }

    suspend fun changeUsername(){
        val changeUsernameReceiveRemote = call.receive<ChangeUsernameReceiveRemote>()
        val userDTO = Users.fetchUser(changeUsernameReceiveRemote.email)

        println("receive -> $changeUsernameReceiveRemote, dto -> $userDTO")

        if (userDTO == null){
            call.respond(HttpStatusCode.BadRequest, "Something went wrong. " +
                    "Current user wasn't found in the database")
        } else {
            Users.updateUsername(
                UserDTO(
                    email = changeUsernameReceiveRemote.email,
                    username = changeUsernameReceiveRemote.newUsername,
                    password = userDTO.password
                )
            )
            call.respond(HttpStatusCode.OK)
        }
    }
}