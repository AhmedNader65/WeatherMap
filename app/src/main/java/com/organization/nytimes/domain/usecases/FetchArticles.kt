package com.organization.nytimes.domain.usecases

import com.organization.nytimes.domain.model.NoMoreArticlesException
import com.organization.nytimes.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchArticles @Inject constructor(private val articlesRepository: WeatherRepository) {

    suspend operator fun invoke(section: String, period: Int) {
        return withContext(Dispatchers.IO) {
            val articles = articlesRepository.requestWeather(section, period)
            if (articles.isEmpty())
                throw NoMoreArticlesException("No more articles")
            articlesRepository.storeWeather(articles)
        }
    }
}