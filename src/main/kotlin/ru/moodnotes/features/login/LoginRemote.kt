package ru.moodnotes.features.login

@kotlinx.serialization.Serializable
data class LoginReceiveRemote(
    val email: String,
    val password: String
)

@kotlinx.serialization.Serializable
data class LoginResponseRemote(
    val token: String
    // уникальный ключ клиента, который не будет постоянно требовать логин и пароль
)