package com.strings.task.database.repository

import com.strings.task.database.entity.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun insert(task: Task)

    suspend fun delete(task: Task)

    suspend fun getTaskById(id : Long): Task?

    fun getTasks(): Flow<List<Task>>
}
