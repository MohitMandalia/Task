package com.strings.task.database.repository

import com.strings.task.database.TaskDao
import com.strings.task.database.entity.Task
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val dao: TaskDao
): TaskRepository {

    override suspend fun insert(task: Task) {
        dao.insert(task)
    }

    override suspend fun delete(task: Task) {
        dao.delete(task)
    }

    override suspend fun getTaskById(id: Long): Task? {
        return dao.getTaskById(id)
    }

    override fun getTasks(): Flow<List<Task>> {
        return dao.getTasks()
    }
}