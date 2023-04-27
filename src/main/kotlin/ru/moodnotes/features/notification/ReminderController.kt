package ru.moodnotes.features.notification

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.moodnotes.database.reminders.ReminderDTO
import ru.moodnotes.database.reminders.Reminders
import ru.moodnotes.database.users.Users

class ReminderController(private val call: ApplicationCall) {

    suspend fun changeReminderTime(){
        val reminderReceiveRemote = call.receive<ReminderReceiveRemote>()
        val notificationDTO = Users.fetchUser(reminderReceiveRemote.email)

        println("receive -> $reminderReceiveRemote, dto -> $notificationDTO")

        if (notificationDTO == null){
            call.respond(HttpStatusCode.BadRequest, "User not found")
        } else {
            Reminders.updateTime(
                ReminderDTO(
                    email = reminderReceiveRemote.email,
                    time = reminderReceiveRemote.time,
                    mode = reminderReceiveRemote.mode
                )
            )
            call.respond(HttpStatusCode.OK)
        }
    }

    suspend fun changeReminderMode(){
        val reminderReceiveRemote = call.receive<ReminderReceiveRemote>()
        val notificationDTO = Users.fetchUser(reminderReceiveRemote.email)

        println("receive -> $reminderReceiveRemote, dto -> $notificationDTO")

        if (notificationDTO == null){
            call.respond(HttpStatusCode.BadRequest, "User not found")
        } else {
            Reminders.updateMode(
                ReminderDTO(
                    email = reminderReceiveRemote.email,
                    time = reminderReceiveRemote.time,
                    mode = reminderReceiveRemote.mode
                )
            )
            call.respond(HttpStatusCode.OK)
        }
    }
}