package ru.moodnotes.database.reminders

import kotlinx.datetime.toJavaLocalTime
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.javatime.time
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

object Reminders : Table() {
    private val email = Reminders.varchar("user_email", 25)
    private val time = Reminders.time("time")
    private val mode = Reminders.bool("mode")

    fun setReminder(reminderDTO: ReminderDTO){
        transaction {
            Reminders.insert {
                it[email] = reminderDTO.email
                it[time] = reminderDTO.time.toJavaLocalTime()
                it[mode] = reminderDTO.mode
            }
        }
    }

    fun updateMode(reminderDTO: ReminderDTO){
        transaction {
            Reminders.update({ email eq reminderDTO.email}) {
                it[mode] = reminderDTO.mode
            }
        }
    }

    fun updateTime(reminderDTO: ReminderDTO){
        transaction {
            Reminders.update({ email eq reminderDTO.email}) {
                it[time] = reminderDTO.time.toJavaLocalTime()
            }
        }
    }
}