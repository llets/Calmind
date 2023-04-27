package ru.moodnotes.database.reminders

import kotlinx.datetime.LocalTime

class ReminderDTO (
    var email: String,
    var time: LocalTime,
    var mode: Boolean
)