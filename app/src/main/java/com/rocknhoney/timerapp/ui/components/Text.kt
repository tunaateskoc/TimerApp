package com.rocknhoney.timerapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimerText(value: String, modifier: Modifier = Modifier) {
    Text(
        text = value,
        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
        modifier = modifier.padding(vertical = 16.dp)
    )
}