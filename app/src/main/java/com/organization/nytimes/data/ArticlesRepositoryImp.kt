package com.organization.nytimes.data

import com.organization.nytimes.data.api.ArticlesApi
import com.organization.nytimes.data.api.model.mapToDomain
import com.organization.nytimes.data.cache.Cache
import com.organization.nytimes.data.cache.model.CachedArticle
import com.organization.nytimes.data.cache.model.CachedImage
import com.organization.nytimes.data.cache.model.toDomain
import com.organization.nytimes.domain.model.Article
import com.organization.nytimes.domain.model.NetworkException
import com.organization.nytimes.domain.repository.ArticlesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import javax.inject.Inject

class ArticlesRepositoryImp @Inject constructor(
    private val cache: Cache,
    private val api: ArticlesApi
) : ArticlesRepository {
    override suspend fun requestArticles(section: String, period: Int): List<Article> {

        try {
            val results = api.getMostViewed(section, period)
            return results.articles.map { it.mapToDomain() }
        } catch (exception: HttpException) {
            throw NetworkException(exception.message ?: "Code ${exception.code()}")
        }
    }

    override suspend fun storeArticles(articles: List<Article>) {
        articles.forEach { article ->
            cache.storeArticles(CachedArticle.fromDomain(article))
            cache.storeImage(*article.media.map { CachedImage.fromDomain(article.id, it) }
                .toTypedArray())
        }
    }

    override fun getArticles(): Flow<List<Article>> {
        return cache.getArticles()
            .distinctUntilChanged() // ensures only events with new information get to the subscriber.
            .map { articlesList ->
                articlesList.map { it.toDomain() }
            }
    }
}