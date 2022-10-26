package com.organization.weathermap.data.cache

import com.organization.weathermap.data.cache.model.*
import kotlinx.coroutines.flow.Flow

interface Cache {

    fun getForecast(name: String): Flow<CachedCityWithForecast>

    suspend fun storeCity(city: CachedCity)

    suspend fun storeForecast(vararg forecast: CachedForecast)
}