package com.strings.task.database

import androidx.room.*
import com.strings.task.database.entity.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM task WHERE taskId = :id")
    suspend fun getTaskById(id : Long): Task?

    @Query("SELECT * FROM task")
    fun getTasks(): Flow<List<Task>>

}
