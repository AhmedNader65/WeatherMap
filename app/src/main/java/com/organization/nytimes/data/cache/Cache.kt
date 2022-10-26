package com.organization.nytimes.data.cache

import com.organization.nytimes.data.cache.model.*
import kotlinx.coroutines.flow.Flow

interface Cache {

    fun getForecast(id: Long): Flow<CachedCityWithForecast>

    suspend fun storeCity(city: CachedCity)

    suspend fun storeForecast(vararg forecast: CachedForecast)
}