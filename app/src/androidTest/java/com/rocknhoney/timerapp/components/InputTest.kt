package com.yougov.aevum.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InputTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun hourTimeInputTest() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TimeInput(
                        placeholderText = "Hour TimeInput",
                        timeInputType = TimeInputType.HOUR,
                        modifier = Modifier.testTag("Hour TimeInput"),
                        onTextChanged = {
                        }
                    )
                }
            }
        }
        val hourTimeInput = composeTestRule.onNodeWithTag("Hour TimeInput")
        hourTimeInput.assertExists()
        hourTimeInput.assertIsDisplayed()
        hourTimeInput.assertTextContains("Hour TimeInput")
        hourTimeInput.assertIsNotFocused()

        hourTimeInput.performClick()
        hourTimeInput.assertIsFocused()

        //Numeric Input
        hourTimeInput.performTextReplacement("12")
        hourTimeInput.assertTextContains("12")

        //Character Input
        hourTimeInput.performTextClearance()
        hourTimeInput.performTextReplacement("Hour Test")
        hourTimeInput.assertTextContains("")
    }

    @Test
    fun minuteTimeInputTest() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TimeInput(
                        placeholderText = "Minute TimeInput",
                        timeInputType = TimeInputType.MINUTE,
                        modifier = Modifier.testTag("Minute TimeInput"),
                        onTextChanged = {
                        }
                    )
                }
            }
        }
        val minuteTimeInput = composeTestRule.onNodeWithTag("Minute TimeInput")
        minuteTimeInput.assertExists()
        minuteTimeInput.assertIsDisplayed()
        minuteTimeInput.assertTextContains("Minute TimeInput")
        minuteTimeInput.assertIsNotFocused()

        minuteTimeInput.performClick()
        minuteTimeInput.assertIsFocused()

        //Numeric Input
        minuteTimeInput.performTextReplacement("25")
        minuteTimeInput.assertTextContains("25")

        //Character Input
        minuteTimeInput.performTextClearance()
        minuteTimeInput.performTextReplacement("Minute Test")
        minuteTimeInput.assertTextContains("")

        //Input equals 60
        minuteTimeInput.performTextClearance()
        minuteTimeInput.performTextReplacement("60")
        minuteTimeInput.assertTextContains("60")

        //Input more than 60
        minuteTimeInput.performTextClearance()
        minuteTimeInput.performTextReplacement("80")
        minuteTimeInput.assertTextContains("")
    }

    @Test
    fun secondTimeInputTest() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TimeInput(
                        placeholderText = "Second TimeInput",
                        timeInputType = TimeInputType.SECOND,
                        modifier = Modifier.testTag("Second TimeInput"),
                        onTextChanged = {
                        }
                    )
                }
            }
        }
        val secondTimeInput = composeTestRule.onNodeWithTag("Second TimeInput")
        secondTimeInput.assertExists()
        secondTimeInput.assertIsDisplayed()
        secondTimeInput.assertTextContains("Second TimeInput")
        secondTimeInput.assertIsNotFocused()

        //Numeric Input
        secondTimeInput.performTextReplacement("25")
        secondTimeInput.assertTextContains("25")

        //Character Input
        secondTimeInput.performTextClearance()
        secondTimeInput.performTextReplacement("Second Test")
        secondTimeInput.assertTextContains("")

        //Input equals 60
        secondTimeInput.performTextClearance()
        secondTimeInput.performTextReplacement("60")
        secondTimeInput.assertTextContains("60")

        //Input more than 60
        secondTimeInput.performTextClearance()
        secondTimeInput.performTextReplacement("100")
        secondTimeInput.assertTextContains("")
    }
}
