package ru.moodnotes.features.changePrivateInfo

@kotlinx.serialization.Serializable
class ChangePasswordReceiveRemote (
    val email: String,
    val oldPassword: String,
    val newPassword: String
)

@kotlinx.serialization.Serializable
class ChangePasswordRespondRemote (
    val isChanged: Boolean
)

@kotlinx.serialization.Serializable
class ChangeUsernameReceiveRemote (
    val oldUsername: String,
    val newUsername: String
)

@kotlinx.serialization.Serializable
class ChangeUsernameRespondRemote (
    val isChanged: Boolean
)