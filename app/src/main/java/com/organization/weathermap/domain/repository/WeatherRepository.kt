package com.organization.weathermap.domain.repository

import com.organization.weathermap.domain.model.City
import kotlinx.coroutines.flow.Flow


interface WeatherRepository {
    suspend fun requestWeather(city: String): City
    suspend fun storeWeather(city: City)
    fun getWeather(name: String): Flow<City>
}