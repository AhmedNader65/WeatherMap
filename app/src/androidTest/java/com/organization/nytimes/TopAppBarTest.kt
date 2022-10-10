package com.organization.nytimes

import androidx.compose.material.Text
import org.junit.Rule
import androidx.compose.ui.test.junit4.createComposeRule
import com.organization.nytimes.ui.main.ArticlesScreen
import org.junit.Test

class TopAppBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun myTest() {
        composeTestRule.setContent {
            ArticlesScreen({})
            Thread.sleep(5000)
        }
    }
}