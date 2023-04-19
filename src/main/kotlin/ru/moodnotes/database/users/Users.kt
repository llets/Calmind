package ru.moodnotes.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

object Users: Table() {
    private val password = Users.varchar("password", 25)
    private val email = Users.varchar("email", 25)
    private val username = Users.varchar("username", 50)

    // CRUD

    fun insert(userDTO: UserDTO){
        transaction {
            Users.insert {
                it[password] = userDTO.password
                it[username] = userDTO.username
                it[email] = userDTO.email
            }
        }
    }

    fun updatePassword(userDTO: UserDTO){
        transaction {
            Users.update {
                it[password] = userDTO.password
            }
        }
    }

    fun updateUsername(userDTO: UserDTO){
        transaction {
            Users.update {
                it[username] = userDTO.username
            }
        }
    }

    fun fetchUser(email: String) : UserDTO? {
        return try {
            transaction {
                val userModel = Users.select { Users.email.eq(email) }.single()
                UserDTO(
                    email = userModel[Users.email],
                    password = userModel[password],
                    username = userModel[username]
                )
            }
        } catch (e: Exception){
            e.printStackTrace()
            null
        }
    }
}