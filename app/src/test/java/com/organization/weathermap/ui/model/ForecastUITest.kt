package com.organization.weathermap.ui.model

import android.icu.util.Calendar
import com.organization.weathermap.domain.model.Forecast
import org.junit.Assert.*
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class ForecastUITest {

    @Test
    fun testMappingFromDomain_resultingForecastUIObject() {
        // GIVEN
        val forecast = Forecast(
            1666882800,
            "2022-10-27 15:00:00",
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
        // WHEN
        val uiForecast = ForecastUI.fromDomain(forecast)

        // THEN

        assertEquals(forecast.date, uiForecast.date)
        assertEquals(forecast.temp, uiForecast.temp)
        assertEquals(forecast.weather, uiForecast.weather)
        assertEquals(forecast.icon, uiForecast.icon)
    }

}