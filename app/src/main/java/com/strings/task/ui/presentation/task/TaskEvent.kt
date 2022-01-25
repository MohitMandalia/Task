package com.strings.task.ui.presentation.task

import com.strings.task.database.entity.Task

sealed class TaskEvent{
    data class OnDeleteTaskClick(val task: Task): TaskEvent()
    data class OnCompletedChange(val task: Task, val isCompleted: Boolean): TaskEvent()
    object OnUndoDeleteClick: TaskEvent()
    data class OnTaskClick(val task: Task): TaskEvent()
    object OnAddTaskClick: TaskEvent()
}
