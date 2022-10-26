package com.organization.weathermap.domain.usecases

import com.organization.weathermap.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchWeather @Inject constructor(private val weatherRepository: WeatherRepository) {

    suspend operator fun invoke(city: String) {
        return withContext(Dispatchers.IO) {
            try {
                val weather = weatherRepository.requestWeather(city)
                weatherRepository.storeWeather(weather)
            } catch (e: Exception) {
                throw e
            }
        }
    }
}