package com.organization.weathermap

import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import com.organization.weathermap.ui.main.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Test

@HiltAndroidTest
class ProgressIndicatorTest {

    @get:Rule()
    val composeRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Before
    fun setup() {
        hiltRule.inject()
    }
    @Test
    fun myTest() {
        composeRule.onNodeWithText("Enter city name...").performTextInput("Cairo")
        composeRule.onNodeWithText("Search").performClick()
        composeRule
            .onNodeWithTag(testTag = "myProgressIndicator")
            .assertIsDisplayed()
            .assertRangeInfoEquals(ProgressBarRangeInfo.Indeterminate)

    }
}