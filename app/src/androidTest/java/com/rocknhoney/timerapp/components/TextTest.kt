package com.rocknhoney.timerapp.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rocknhoney.timerapp.ui.components.TimerText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TextTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun timeTextTest() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TimerText(
                        value = "Timer Text",
                        modifier = Modifier.testTag(
                            "Timer Text"
                        )
                    )
                }
            }
        }
        val timerText = composeTestRule.onNodeWithTag("Timer Text")
        timerText.assertExists()
        timerText.assertIsDisplayed()
        timerText.assertTextContains("Timer Text")
    }
}