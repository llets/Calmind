package ru.moodnotes.features.notification

import kotlinx.datetime.LocalTime

@kotlinx.serialization.Serializable
data class ReminderReceiveRemote (
    val email: String,
    val time: LocalTime,
    val mode: Boolean
)