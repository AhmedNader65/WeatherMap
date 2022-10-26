package com.organization.nytimes.domain.repository

import com.organization.nytimes.domain.model.City
import kotlinx.coroutines.flow.Flow


interface WeatherRepository {
    suspend fun requestWeather(city: String): City
    suspend fun storeWeather(city: City)
    fun getWeather(id: Long): Flow<City>
}