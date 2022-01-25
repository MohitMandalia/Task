package com.strings.task.ui.presentation.task_add_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.strings.task.database.entity.Task
import com.strings.task.database.repository.TaskRepository
import com.strings.task.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val repository: TaskRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var task by mutableStateOf<Task?>(null)
        private set

    var taskName by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init{
        val taskId = savedStateHandle.get<Long>("taskId")!!
        if(taskId != -1L){
            viewModelScope.launch {
                repository.getTaskById(taskId)?.let{ task ->
                    taskName = task.taskName
                    description = task.taskDescription ?: ""
                    this@AddEditTaskViewModel.task = task
                }
            }
        }
    }

    fun onEvent(event: AddEditTaskEvent){
        when(event){
            is AddEditTaskEvent.OnTaskNameChange -> {
                taskName = event.taskName
            }
            is AddEditTaskEvent.OnTaskDescriptionChange -> {
                description = event.taskDescription
            }
            is AddEditTaskEvent.OnSaveTaskClick -> {
                viewModelScope.launch {
                    if (taskName.isBlank()){
                        sendUiEvent(UiEvent.ShowSnackbar(
                            message = "Task Name cannot be empty"
                        ))
                        return@launch
                    }
                    repository.insert(
                        Task(
                            taskId = task?.taskId,
                            taskName = taskName,
                            taskDescription = description,
                            isCompleted = task?.isCompleted ?: false
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
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