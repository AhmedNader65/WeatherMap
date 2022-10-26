package com.organization.nytimes.data

import com.organization.nytimes.data.api.WeatherApi
import com.organization.nytimes.data.api.model.mapToDomain
import com.organization.nytimes.data.cache.Cache
import com.organization.nytimes.data.cache.model.*
import com.organization.nytimes.domain.model.Article
import com.organization.nytimes.domain.model.City
import com.organization.nytimes.domain.model.NetworkException
import com.organization.nytimes.domain.repository.WeatherRepository
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
        cache.storeForecast(*city.weather.map { CachedForecast.fromDomain(city.id,it) }.toTypedArray())
    }

    override fun getWeather(id: Long): Flow<City> {
        TODO("Not yet implemented")
    }
}