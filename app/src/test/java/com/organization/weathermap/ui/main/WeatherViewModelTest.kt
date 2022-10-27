package com.organization.weathermap.ui.main

import android.icu.util.Calendar
import com.organization.weathermap.data.FakeRepository
import com.organization.weathermap.domain.model.Forecast
import com.organization.weathermap.domain.repository.WeatherRepository
import com.organization.weathermap.domain.usecases.FetchWeather
import com.organization.weathermap.domain.usecases.GetWeather
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class WeatherViewModelTest {

    private lateinit var repository: WeatherRepository

    lateinit var viewModel: WeatherViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        repository = FakeRepository()

        viewModel = WeatherViewModel(
            GetWeather(repository),
            FetchWeather(repository), UnconfinedTestDispatcher()
        )
    }

    @Test
    fun testGetForecastByCity_cityExists_returnsForecast() = runBlocking {
        // Given
        repository.requestWeather("Cairo")

        val format = "yyyy-MM-dd"
        val formatter = SimpleDateFormat(format)
        val time = formatter.format(Date().time)

        val forecast = Forecast(
            1666882800,
            "$time 15:00:00",
            "301",
            "301",
            "301",
            "301",
            "1016",
            "1016",
            "1013",
            "42",
            "-.17",
            "broken clouds",
            "04d",
            "52",
            "4.95",
            10000
        )

        // When
        viewModel.getWeatherById("Cairo")
        val res = viewModel.forecasts.first { it != null }!!
        // Then
        assertEquals(forecast.temp, res.first().temp)
    }


}