package com.yougov.aevum.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.yougov.aevum.viewmodel.TimerViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import kotlin.concurrent.schedule

@RunWith(AndroidJUnit4::class)
class TimersScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun timersScreenTest() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TimersScreen(timerViewModel = TimerViewModel())
                }
            }
        }
        val headerText = composeTestRule.onNodeWithTag("Header Text")
        headerText.assertExists()
        headerText.assertIsDisplayed()
        headerText.assertTextContains("There is no running timers!")

        var timerList = composeTestRule.onNodeWithTag("Timer List")
        timerList.assertDoesNotExist()

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

        headerText.assertExists()
        headerText.assertIsDisplayed()
        headerText.assertTextContains("Running timers:")


        //Check Timer List is visible
        timerList = composeTestRule.onNodeWithTag("Timer List")
        timerList.assertExists()
        timerList.assertIsDisplayed()

        val timerText = composeTestRule.onNodeWithTag("Timer Text")

        timerText.assertExists()
        timerText.assertIsDisplayed()
        timerText.assertTextContains("01:02:02")

        asyncTimer(2000)
        timerText.assertTextContains("01:02:00")

        //Second Input
        minuteTimeInput.performClick()
        minuteTimeInput.assertIsFocused()
        minuteTimeInput.performTextReplacement("5")
        minuteTimeInput.assertTextContains("5")

        secondTimeInput.performClick()
        secondTimeInput.assertIsFocused()
        secondTimeInput.performTextReplacement("1")
        secondTimeInput.assertTextContains("1")

        startButton.performClick()

        timerText.assertExists()
        timerText.assertIsDisplayed()
        timerText.assertTextContains("00:05:00")

        asyncTimer(2000)

        timerText.assertTextContains("00:04:58")


    }

    private fun asyncTimer(delay: Long = 1000) {
        AsyncTimer.start(delay)
        composeTestRule.waitUntil(
            condition = { AsyncTimer.expired },
            timeoutMillis = delay + 1000
        )
    }

    object AsyncTimer {
        var expired = false
        fun start(delay: Long) {
            expired = false
            Timer().schedule(delay) {
                expired = true
            }
        }
    }
}
