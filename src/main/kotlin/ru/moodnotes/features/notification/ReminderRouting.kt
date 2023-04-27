package ru.moodnotes.features.notification

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureReminderRouting() {
    routing {
        post("/changeReminderTime") {
            val reminderController = ReminderController(call)
            reminderController.changeReminderTime()
        }
        post("/changeReminderMode") {
            val reminderController = ReminderController(call)
            reminderController.changeReminderMode()
        }
    }
}