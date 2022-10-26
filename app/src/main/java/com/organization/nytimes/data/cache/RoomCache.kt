package com.organization.nytimes.data.cache

import com.organization.nytimes.data.cache.daos.WeatherDao
import com.organization.nytimes.data.cache.model.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomCache @Inject constructor(
    private val weatherDao: WeatherDao,
) : Cache {
    override fun getForecast(id: Long): Flow<CachedCityWithForecast> = weatherDao.getForecast(id)

    override suspend fun storeCity(city: CachedCity) = weatherDao.insertCity(city)

    override suspend fun storeForecast(vararg forecast: CachedForecast) =
        weatherDao.insertForecast(*forecast)
}