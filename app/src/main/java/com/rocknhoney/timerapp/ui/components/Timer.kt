package com.yougov.aevum.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rocknhoney.timerapp.ui.components.TimeInput
import com.rocknhoney.timerapp.ui.components.TimeInputType

/**
 * @param onButtonClicked is used to inform button clicking events of the timer.
 */
@Composable
fun Timer(
    onButtonClicked: (String) -> Unit
) {
    var hoursInput by remember { mutableStateOf("") }
    var minutesInput by remember { mutableStateOf("") }
    var secondsInput by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TimeInput(
                placeholderText = "hours",
                modifier = Modifier.weight(1f).testTag("Hour TimeInput"),
                timeInputType = TimeInputType.HOUR,
                text = hoursInput,
            ) {
                hoursInput = it
            }
            TimeInput(
                placeholderText = "minutes",
                modifier = Modifier.weight(1f).testTag("Minute TimeInput"),
                timeInputType = TimeInputType.MINUTE,
                text = minutesInput,
            ) {
                minutesInput = it
            }
            TimeInput(
                placeholderText = "seconds",
                modifier = Modifier.weight(1f).testTag("Second TimeInput"),
                timeInputType = TimeInputType.SECOND,
                text = secondsInput,
            ) {
                secondsInput = it
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .testTag("Start Button"),
            onClick = {
                if (hoursInput.isBlank()) {
                    hoursInput = "00"
                }
                if (minutesInput.isBlank()) {
                    minutesInput = "00"
                }
                if (secondsInput.isBlank()) {
                    secondsInput = "00"
                }
                onButtonClicked("$hoursInput:$minutesInput:$secondsInput")
                hoursInput = ""
                minutesInput = ""
                secondsInput = ""
                focusManager.clearFocus()
            },
        ) {
            Text("Start!", style = TextStyle(fontSize = 25.sp))
        }
    }
}