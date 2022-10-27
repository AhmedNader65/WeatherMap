package com.organization.weathermap.data.cache

import com.organization.weathermap.data.cache.daos.WeatherDao
import com.organization.weathermap.data.cache.model.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomCache @Inject constructor(
    private val weatherDao: WeatherDao,
) : Cache {
    override fun getForecast(name: String): Flow<CachedCityWithForecast> {
        val res = weatherDao.getForecast(name)
        return res
    }

    override suspend fun storeCity(city: CachedCity) = weatherDao.insertCity(city)

    override suspend fun storeForecast(vararg forecast: CachedForecast) {
        weatherDao.deleteByCity(forecast.first().cityId)
        return weatherDao.insertForecast(*forecast)
    }
}