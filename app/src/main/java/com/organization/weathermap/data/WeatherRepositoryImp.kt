package com.organization.weathermap.data

import com.organization.weathermap.data.api.WeatherApi
import com.organization.weathermap.data.api.model.mapToDomain
import com.organization.weathermap.data.cache.Cache
import com.organization.weathermap.data.cache.model.CachedCity
import com.organization.weathermap.data.cache.model.CachedForecast
import com.organization.weathermap.data.cache.model.toDomain
import com.organization.weathermap.domain.model.City
import com.organization.weathermap.domain.model.NetworkException
import com.organization.weathermap.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val cache: Cache, private val api: WeatherApi
) : WeatherRepository {
    override suspend fun requestWeather(city: String): City {

        try {
            val results = api.getForecast(city = city)
            return results.mapToDomain()
        } catch (exception: HttpException) {
            throw NetworkException(exception.message ?: "Code ${exception.code()}")
        }
    }

    override suspend fun storeWeather(city: City) {
        cache.storeCity(CachedCity.fromDomain(city))
        cache.storeForecast(*city.weather.map { CachedForecast.fromDomain(city.id, it) }
            .toTypedArray())
    }

    override fun getWeather(name:String): Flow<City> =
        cache.getForecast(name).distinctUntilChanged().map {
            it.toDomain()
        }
}