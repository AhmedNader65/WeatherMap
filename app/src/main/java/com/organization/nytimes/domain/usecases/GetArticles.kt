package com.organization.nytimes.domain.usecases

import com.organization.nytimes.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

class GetArticles @Inject constructor(private val articlesRepository: WeatherRepository) {

    operator fun invoke() = articlesRepository.getWeather()
        .filter { it.isNotEmpty() }
}