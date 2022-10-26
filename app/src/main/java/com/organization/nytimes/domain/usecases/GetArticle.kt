package com.organization.nytimes.domain.usecases

import com.organization.nytimes.domain.repository.WeatherRepository
import javax.inject.Inject

class GetArticle @Inject constructor(private val articlesRepository: WeatherRepository) {

    operator fun invoke(id: Long) = articlesRepository.getArticle(id)
}