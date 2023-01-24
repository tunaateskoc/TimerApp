package com.rocknhoney.timerapp.extensions

import com.rocknhoney.timerapp.util.Util
import com.rocknhoney.timerapp.util.extensions.convertToDateFormat
import com.rocknhoney.timerapp.util.extensions.dateToMilliSeconds
import junit.framework.TestCase.assertEquals
import org.junit.Test

class DateExtTest {
    @Test
    fun convertToDateFormat() {
        assertEquals("00:01:00", 60000L.convertToDateFormat(Util.DATE_FORMAT))
    }

    @Test
    fun dateToMilliSeconds() {
        val timeString = "00:01:00".dateToMilliSeconds(Util.DATE_FORMAT)
        assertEquals(60000L, timeString)
    }
}
