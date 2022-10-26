package com.organization.weathermap.data

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.organization.weathermap.data.api.model.ApiContainer
import com.organization.weathermap.data.api.model.mapToDomain
import com.organization.weathermap.data.cache.model.CachedArticle
import com.organization.weathermap.data.cache.model.toDomain
import com.organization.weathermap.data.utils.JsonReader
import com.organization.weathermap.domain.model.Article
import com.organization.weathermap.domain.repository.ArticlesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeRepository @Inject constructor() : ArticlesRepository {
    var apiResponse: ApiContainer

    init {
        val gson = GsonBuilder().serializeNulls().create();
        val type = object : TypeToken<ApiContainer>() {}.type
        val articlesRes = JsonReader.getJson("articles.json")
        apiResponse = gson.fromJson(articlesRes, type)!!
    }

    val localArticles: List<CachedArticle> get() = mutableLocalArticles
    private val mutableLocalArticles = mutableListOf<CachedArticle>()
    override suspend fun requestArticles(section: String, period: Int): List<Article> {

        return apiResponse.articles.map { it.mapToDomain() }
    }

    override suspend fun storeArticles(articles: List<Article>) {
        mutableLocalArticles.addAll(articles.map { CachedArticle.fromDomain(it) })
    }

    override fun getArticles(): Flow<List<Article>> {
        return flow {
            emit(localArticles.map { it.toDomain() })
        }
    }

    override fun getArticle(id: Long): Flow<Article> {
        return flow {
            localArticles.filter{ it.articleId == id }.firstOrNull()?.let { emit(it.toDomain()) }
        }
    }
}