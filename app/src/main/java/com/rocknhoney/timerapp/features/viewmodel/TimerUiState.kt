package com.rocknhoney.timerapp.features.viewmodel

sealed class TimerUiState {
    object Empty : TimerUiState()
    class TimerRunning(val data: ArrayList<String>) : TimerUiState()
    class Error(val message: String, val data: ArrayList<String>) : TimerUiState()
}
