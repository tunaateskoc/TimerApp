package com.rocknhoney.timerapp.features.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rocknhoney.timerapp.util.extensions.convertToDateFormat
import com.rocknhoney.timerapp.util.extensions.dateToMilliSeconds
import com.rocknhoney.timerapp.util.Util
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TimerViewModel : ViewModel() {
    var remainingTime = 0L

    private var countDownTimer: CountDownTimer? = null

    private val _timeValues = MutableStateFlow<List<Long>>(arrayListOf())

    private val _uiState = MutableStateFlow<TimerUiState>(TimerUiState.Empty)
    val uiState = _uiState.asStateFlow()


    fun addTimeToList(newTime: String) {
        val newTimeInMillis = newTime.dateToMilliSeconds(Util.DATE_FORMAT)
        val tempList = _timeValues.value.toMutableList()
        tempList.add(newTimeInMillis)
        _timeValues.value = tempList
        startTimer(newTimeInMillis)
    }

    fun allInputsAreEmpty() {
        _uiState.value = TimerUiState.Error(
            message = "In order to start the timer at least 1 input should be filled",
            data = convertTimesElementsToString()
        )
    }

    private fun startTimer(newTime: Long) {
        if (newTime > remainingTime) {
            pauseTimer()
            countDownTimer = object : CountDownTimer(newTime, Util.ONE_SECOND) {

                override fun onTick(millisRemaining: Long) {
                    remainingTime = millisRemaining
                    viewModelScope.launch {
                        _timeValues.value = updateList()
                        _uiState.emit(TimerUiState.TimerRunning(convertTimesElementsToString()))
                    }
                }

                override fun onFinish() {
                    pauseTimer()
                }
            }.start()
        }
    }

    private fun pauseTimer() {
        countDownTimer?.cancel()
    }

    private fun convertTimesElementsToString(): ArrayList<String> {
        val tempList = _timeValues.value.toMutableList()
        val timesList = arrayListOf<String>()
        for (i in tempList.indices) {
            timesList.add(tempList[i].convertToDateFormat(Util.DATE_FORMAT))
        }
        return timesList
    }

    private fun updateList(): MutableList<Long> {
        val tempList = _timeValues.value.toMutableList()
        for (i in tempList.indices) {
            if (tempList[i] > 0L) {
                tempList[i] = tempList[i] - Util.ONE_SECOND
            }
        }
        return tempList
    }
}

