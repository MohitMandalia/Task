package com.strings.task.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.strings.task.database.TaskDatabase
import com.strings.task.database.repository.TaskRepository
import com.strings.task.database.repository.TaskRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskDatabase(app: Application) : TaskDatabase{
        return Room.databaseBuilder(
            app,
            TaskDatabase::class.java,
            "task_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(database: TaskDatabase) : TaskRepository{
        return TaskRepositoryImpl(database.dao)
    }

}