package com.strings.task.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true) val taskId: Long? = 0L,
    val taskName: String,
    val taskDescription: String?,
    val isCompleted: Boolean = false,
    val taskDate: Long? = 0L,
    val taskTime: Long? = 0L
)
