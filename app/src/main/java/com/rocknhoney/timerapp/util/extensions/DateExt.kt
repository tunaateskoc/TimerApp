package com.rocknhoney.timerapp.util.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.convertToDateFormat(dateFormat: String): String {
    return SimpleDateFormat(dateFormat).format(this)
}

fun String.dateToMilliSeconds(dateFormat: String): Long {
    val simpleDateFormat = SimpleDateFormat(dateFormat)
    val date: Date = simpleDateFormat.parse(this)
    return date.time
}