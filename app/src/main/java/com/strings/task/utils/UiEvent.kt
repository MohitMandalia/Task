package com.strings.task.utils

sealed class UiEvent {
    object PopBackStack : UiEvent()
    data class Nagivate(val route: String) : UiEvent()
    data class ShowSnackbar(
        val message: String,
        val action: String? = null
    ): UiEvent()
}