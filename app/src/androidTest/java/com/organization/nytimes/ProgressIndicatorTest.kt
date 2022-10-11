package com.organization.nytimes

import androidx.compose.material.Text
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertRangeInfoEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.organization.nytimes.ui.main.ArticlesScreen
import com.organization.nytimes.ui.main.MainActivity
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
        composeRule
            .onNodeWithTag(testTag = "myProgressIndicator")
            .assertIsDisplayed()
            .assertRangeInfoEquals(ProgressBarRangeInfo.Indeterminate)

    }
}