package com.organization.weathermap

import androidx.activity.compose.setContent
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.organization.weathermap.data.FakeRepository
import com.organization.weathermap.data.di.DataModule
import com.organization.weathermap.domain.repository.WeatherRepository
import com.organization.weathermap.ui.main.MainActivity
import com.organization.weathermap.ui.main.WEATHER_LIST_TEST_TAG
import com.organization.weathermap.ui.main.WeatherScreen
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(
    DataModule::class
)
class MainActivityTest {

    @get:Rule()
    val composeRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Module
    @InstallIn(ActivityRetainedComponent::class)
    abstract class DataModule {
        @Binds
        @ActivityRetainedScoped
        abstract fun bindWeatherRepository(repository: FakeRepository): WeatherRepository
    }

    @Inject
    lateinit var repository: FakeRepository

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testFirstItemInTheList_displayingFirstTemp() {

        composeRule.activity.runOnUiThread {
            composeRule.activity.setContent {
                WeatherScreen(time = 1666882200)
            }
        }
        runTest {
            composeRule.onNodeWithText("Enter city name...").performTextInput("Cairo")
            composeRule.onNodeWithText("Search").performClick()
            repository.storeWeather(repository.requestWeather("Cairo"))
            repository.getWeather("Cairo").first()

            composeRule
                .onNodeWithTag(testTag = WEATHER_LIST_TEST_TAG)
                .onChildren()[2].assert(hasText(repository.localForecast.first().temp+"°C"))
        }
    }

}