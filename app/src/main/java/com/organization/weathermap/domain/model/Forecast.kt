package com.organization.weathermap.domain.model

data class City(
    val id: Long,
    val name: String,
    val country: String,
    val population: Long,
    val lat: Double,
    val lng: Double,
    val sunrise: Long,
    val sunset: Long,
    val weather: List<Forecast>
)

data class Forecast(
    val date: Long,
    val formattedDate: String,
    val temp: String,
    val feels_like: String,
    val temp_min: String,
    val temp_max: String,
    val pressure: String,
    val sea_level: String,
    val grnd_level: String,
    val humidity: String,
    val temp_kf: String,
    val weather: String,
    val cloud: String,
    val windSpeed: String,
    val visibility: Int,
)
