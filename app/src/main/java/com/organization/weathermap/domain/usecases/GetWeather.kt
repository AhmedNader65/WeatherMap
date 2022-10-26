package com.organization.weathermap.domain.usecases

import com.organization.weathermap.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeather @Inject constructor(private val weatherRepository: WeatherRepository) {

    operator fun invoke(name: String) = weatherRepository.getWeather(name)
}