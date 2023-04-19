package ru.moodnotes.features.changePrivateInfo

@kotlinx.serialization.Serializable
class ChangePIReceiveRemote (
    val email: String,
    val oldPassword: String,
    val newPassword: String
)

@kotlinx.serialization.Serializable
class ChangePIRespondRemote (
    val isChanged: String
)