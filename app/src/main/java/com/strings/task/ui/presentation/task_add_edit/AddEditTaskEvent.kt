package com.strings.task.ui.presentation.task_add_edit

sealed class AddEditTaskEvent {
    data class OnTaskNameChange(val taskName: String): AddEditTaskEvent()
    data class OnTaskDescriptionChange(val taskDescription: String): AddEditTaskEvent()
    object OnSaveTaskClick: AddEditTaskEvent()
}
