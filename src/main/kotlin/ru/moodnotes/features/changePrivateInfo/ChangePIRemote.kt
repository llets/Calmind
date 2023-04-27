package ru.moodnotes.features.changePrivateInfo

@kotlinx.serialization.Serializable
class ChangePasswordReceiveRemote (
    val email: String,
    val oldPassword: String,
    val newPassword: String
)

@kotlinx.serialization.Serializable
class ChangeUsernameReceiveRemote (
    val email: String,
    val newUsername: String
)