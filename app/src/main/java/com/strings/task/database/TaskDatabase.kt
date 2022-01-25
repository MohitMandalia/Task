package com.strings.task.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.strings.task.database.entity.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase(){

    abstract val dao: TaskDao
}