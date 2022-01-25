package com.strings.task.ui.presentation.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.strings.task.database.entity.Task
import com.strings.task.database.repository.TaskRepository
import com.strings.task.utils.Routes
import com.strings.task.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    val tasks = repository.getTasks()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedTask: Task? = null

    fun onEvent(event: TaskEvent){
        when(event){
            is TaskEvent.OnTaskClick -> {
                sendUiEvent(UiEvent.Nagivate(Routes.ADD_EDIT_TASK + "?taskId=${event.task.taskId}"))
            }
            is TaskEvent.OnAddTaskClick -> {
                sendUiEvent(UiEvent.Nagivate(Routes.ADD_EDIT_TASK))
            }
            is TaskEvent.OnUndoDeleteClick -> {
                deletedTask?.let { task ->
                    viewModelScope.launch {
                        repository.insert(task)
                    }
                }
            }
            is TaskEvent.OnDeleteTaskClick -> {
                viewModelScope.launch {
                    deletedTask = event.task
                    repository.delete(event.task)
                    sendUiEvent(UiEvent.ShowSnackbar(message = "Task Deleted", action = "Undo"))
                }
            }
            is TaskEvent.OnCompletedChange -> {
                viewModelScope.launch {
                    repository.insert(event.task.copy( isCompleted = event.isCompleted))
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}