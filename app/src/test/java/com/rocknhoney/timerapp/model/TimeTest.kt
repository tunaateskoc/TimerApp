package com.yougov.aevum.model

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test


class TimeTest {

    private lateinit var time: Time

    @Before
    fun setUp() {
        time = Time("11:00")
    }

    @Test
    fun getTime() {
        assertEquals("11:00", time.value)
    }

}