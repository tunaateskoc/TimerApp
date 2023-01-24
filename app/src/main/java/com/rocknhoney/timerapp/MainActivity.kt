package com.rocknhoney.timerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import com.rocknhoney.timerapp.features.screen.TimersScreen
import com.rocknhoney.timerapp.ui.theme.TimerAppTheme
import com.rocknhoney.timerapp.features.viewmodel.TimerViewModel

class MainActivity : ComponentActivity() {

    private val timerViewModel: TimerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimerAppTheme {
                TimersScreen(timerViewModel)
            }
        }
    }
}