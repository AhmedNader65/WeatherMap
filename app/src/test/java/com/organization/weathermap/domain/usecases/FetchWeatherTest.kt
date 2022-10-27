package com.organization.weathermap.domain.usecases

import android.icu.util.Calendar
import com.organization.weathermap.data.FakeRepository
import com.organization.weathermap.domain.model.Forecast
import com.organization.weathermap.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date

class UseCasesTest {

    private lateinit var repository: WeatherRepository

    private lateinit var getWeatherUseCase: GetWeather
    private lateinit var fetchWeatherUseCase: FetchWeather


    @Before
    fun setUp() {
        repository = FakeRepository()
        fetchWeatherUseCase = FetchWeather(repository)
        this.getWeatherUseCase = GetWeather(repository)
    }


    @Test
    fun testGetForecastByCity() = runBlocking {
        // Given

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
        try {
            fetchWeatherUseCase("Cairo")
        }catch (e:Exception){
            e.printStackTrace()
        }
        val result = getWeatherUseCase("Cairo")

        // Then
        assertEquals(forecast.temp, result.first().weather.first().temp)
    }

}