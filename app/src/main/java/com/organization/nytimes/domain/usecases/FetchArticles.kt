package com.organization.nytimes.domain.usecases

import com.organization.nytimes.domain.model.NoMoreArticlesException
import com.organization.nytimes.domain.repository.ArticlesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchArticles @Inject constructor(private val articlesRepository: ArticlesRepository) {

    suspend operator fun invoke(section: String, period: Int) {
        return withContext(Dispatchers.IO) {
            val articles = articlesRepository.requestArticles(section, period)
            if (articles.isEmpty())
                throw NoMoreArticlesException("No more articles")
            articlesRepository.storeArticles(articles)
        }
    }
}