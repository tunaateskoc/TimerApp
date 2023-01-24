package com.rocknhoney.timerapp.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

/**
 * Determine the type of the TimeInput.
 * In this case we have 3 different which are HOUR, MINUTE, SECOND.
 */
enum class TimeInputType {
    HOUR,
    MINUTE,
    SECOND
}

/**
 * @param placeholderText using for determining the placeholder text of the TextInput.
 * @param modifier using for modifier injection.
 * @param timeInputType using for determine the type of the input.
 * @param isTextCleared using for managing text clearance events.
 * @param onTextChanged for handling text change events in input.
 */
@Composable
fun TimeInput(
    placeholderText: String,
    text: String = "",
    modifier: Modifier = Modifier,
    timeInputType: TimeInputType,
    onTextChanged: (String) -> Unit
) {
    var input by remember { mutableStateOf(text) }

    TextField(
        value = input,
        maxLines = 1,
        onValueChange = {
            try {
                val value = if (it != "") it.toInt() else 0
                when (timeInputType) {
                    TimeInputType.HOUR -> {
                        input = it
                        onTextChanged(it)
                    }
                    TimeInputType.MINUTE -> {
                        if (value <= 60) {
                            input = it
                            onTextChanged(it)
                        }
                    }
                    TimeInputType.SECOND -> {
                        if (value <= 60) {
                            input = it
                            onTextChanged(it)
                        }
                    }
                }
            } catch (exception: Exception) {

            }
        },
        placeholder = {
            Text(
                text = placeholderText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(fontSize = 14.sp)
            )
        },
        modifier = modifier,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
    )
    LaunchedEffect(key1 = text, block = {
        input = text
        onTextChanged(text)
    })
}

