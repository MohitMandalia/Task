package com.strings.task.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subTask")
data class SubTask(
    @PrimaryKey(autoGenerate = true) val subTaskId: Long = 0L,
    val subTaskName: String,
    val subtaskDescription: String?,
    val isCompleted: Boolean,
    val subTaskDate: Long?,
    val SubTaskTime: Long?,
)
