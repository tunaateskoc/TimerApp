package com.yougov.aevum.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TimerTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun timerTest() {
        var text = ""
        composeTestRule.setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Timer(onButtonClicked = {
                        text = it
                    })
                }
            }
        }
        val hourTimeInput = composeTestRule.onNodeWithTag("Hour TimeInput")
        hourTimeInput.assertExists()
        hourTimeInput.assertIsDisplayed()
        hourTimeInput.assertTextContains("hours")
        hourTimeInput.assertIsNotFocused()

        val minuteTimeInput = composeTestRule.onNodeWithTag("Minute TimeInput")
        minuteTimeInput.assertExists()
        minuteTimeInput.assertIsDisplayed()
        minuteTimeInput.assertTextContains("minutes")
        minuteTimeInput.assertIsNotFocused()

        val secondTimeInput = composeTestRule.onNodeWithTag("Second TimeInput")
        secondTimeInput.assertExists()
        secondTimeInput.assertIsDisplayed()
        secondTimeInput.assertTextContains("seconds")
        secondTimeInput.assertIsNotFocused()

        val startButton = composeTestRule.onNodeWithTag("Start Button")
        startButton.assertExists()
        startButton.assertIsDisplayed()
        startButton.assertTextContains("Start!")
        startButton.assertHasClickAction()

        //Hour Input
        hourTimeInput.performClick()
        hourTimeInput.assertIsFocused()
        hourTimeInput.performTextReplacement("1")
        hourTimeInput.assertTextContains("1")

        //Minute Input
        minuteTimeInput.performClick()
        minuteTimeInput.assertIsFocused()
        minuteTimeInput.performTextReplacement("2")
        minuteTimeInput.assertTextContains("2")

        //Second Input
        secondTimeInput.performClick()
        secondTimeInput.assertIsFocused()
        secondTimeInput.performTextReplacement("3")
        secondTimeInput.assertTextContains("3")

        startButton.performClick()

        //Check input is correct
        assertEquals("1:2:3", text)

        startButton.performClick()

        //Check input when inputs are empty
        assertEquals("00:00:00", text)

        //Check Inputs are cleared after button click
        hourTimeInput.assertTextContains("")
        minuteTimeInput.assertTextContains("")
        secondTimeInput.assertTextContains("")

    }
}
