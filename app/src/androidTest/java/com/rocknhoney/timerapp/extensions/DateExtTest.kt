package com.yougov.aevum.extensions

import com.yougov.aevum.util.Util
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
