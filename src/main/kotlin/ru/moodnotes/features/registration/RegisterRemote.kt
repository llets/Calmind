package ru.moodnotes.features.registration

@kotlinx.serialization.Serializable
data class RegisterReceiveRemote(
    val email: String,
    val password: String
)

@kotlinx.serialization.Serializable
data class RegisterResponseRemote(
    val token: String
)
