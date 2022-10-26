package com.organization.weathermap.ui.model

import com.organization.weathermap.domain.model.Forecast
import java.io.Serializable


class ForecastUI(
    val date: String,
    val weather: String,
) : Serializable {
    companion object {
        fun fromDomain(forecast: Forecast): ForecastUI {
            return ForecastUI(
                forecast.formattedDate,
                forecast.weather
            )
        }
    }
}