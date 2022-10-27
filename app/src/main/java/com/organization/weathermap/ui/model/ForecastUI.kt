package com.organization.weathermap.ui.model

import android.text.format.DateUtils
import com.organization.weathermap.domain.model.Forecast
import java.io.Serializable
import java.text.SimpleDateFormat


class ForecastUI(
    val dateFormatted: String,
    val date: Long,
    val icon: String,
    val temp: String,
    val weather: String,
) : Serializable {
    companion object {
        fun fromDomain(forecast: Forecast): ForecastUI {
            return ForecastUI(
                forecast.formattedDate,
                forecast.date,
                forecast.icon,
                forecast.temp,
                forecast.weather
            )
        }
    }

    fun getRelativeDate(): String {
        val format = "hh:mm a"
        val formatter = SimpleDateFormat(format)
        val time = formatter.format(date * 1000)
        return DateUtils.getRelativeTimeSpanString(
            date * 1000, System.currentTimeMillis(), DateUtils.DAY_IN_MILLIS
        ).toString()+ "  "+ time


    }
}