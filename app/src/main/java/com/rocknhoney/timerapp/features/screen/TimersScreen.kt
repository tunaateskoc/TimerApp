package com.rocknhoney.timerapp.features.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rocknhoney.timerapp.ui.components.Timer
import com.rocknhoney.timerapp.ui.components.TimerText
import com.rocknhoney.timerapp.features.viewmodel.TimerUiState
import com.rocknhoney.timerapp.features.viewmodel.TimerViewModel

@Composable
fun TimersScreen(timerViewModel: TimerViewModel) {
    val context = LocalContext.current
    var headerText by remember { mutableStateOf("There is no running timers!") }
    Surface {
        Column(modifier = Modifier.padding(16.dp)) {
            Timer {
                if (it == "00:00:00") {
                    timerViewModel.allInputsAreEmpty()
                } else {
                    timerViewModel.addTimeToList(it)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                headerText,
                style = TextStyle(fontSize = 30.sp),
                modifier = Modifier.testTag("Header Text")
            )
            when (val state = timerViewModel.uiState.collectAsState().value) {
                TimerUiState.Empty -> {
                    headerText = "There is no running timers!"
                }
                is TimerUiState.Error -> {
                    headerText = "Running timers:"
                    Spacer(modifier = Modifier.height(8.dp))

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .testTag("Timer List")
                    ) {
                        items(state.data) { timer ->
                            Divider()
                            TimerText(timer)
                        }
                    }
                    Toast.makeText(
                        context,
                        state.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is TimerUiState.TimerRunning -> {
                    headerText = "Running timers:"
                    Spacer(modifier = Modifier.height(8.dp))

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .testTag("Timer List")
                    ) {
                        items(state.data.reversed()) { timer ->
                            Divider()
                            TimerText(timer, modifier = Modifier.testTag("Timer Text"))
                        }
                    }
                }
            }
        }
    }
}