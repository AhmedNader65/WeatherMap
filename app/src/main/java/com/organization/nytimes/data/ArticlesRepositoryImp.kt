package com.organization.nytimes.data

import com.organization.nytimes.data.api.ArticlesApi
import com.organization.nytimes.data.api.model.mapToDomain
import com.organization.nytimes.domain.model.Article
import com.organization.nytimes.domain.model.NetworkException
import com.organization.nytimes.domain.repository.ArticlesRepository
import retrofit2.HttpException
import javax.inject.Inject

class ArticlesRepositoryImp @Inject constructor(
    private val api: ArticlesApi
) : ArticlesRepository {
    override suspend fun requestArticles(section:String, period: Int): List<Article> {

        try {
            val results = api.getMostViewed(section, period)
            return results.articles.map { it.mapToDomain() }
        } catch (exception: HttpException) {
            throw NetworkException(exception.message ?: "Code ${exception.code()}")
        }
    }
}