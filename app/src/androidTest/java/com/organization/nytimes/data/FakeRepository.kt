package com.organization.nytimes.data

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.organization.nytimes.data.api.model.ApiContainer
import com.organization.nytimes.data.api.model.mapToDomain
import com.organization.nytimes.data.cache.model.CachedArticle
import com.organization.nytimes.data.cache.model.toDomain
import com.organization.nytimes.data.utils.JsonReader
import com.organization.nytimes.domain.model.Article
import com.organization.nytimes.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeRepository @Inject constructor() : WeatherRepository {
    var apiResponse: ApiContainer

    init {
        val gson = GsonBuilder().serializeNulls().create();
        val type = object : TypeToken<ApiContainer>() {}.type
        val articlesRes = JsonReader.getJson("articles.json")
        apiResponse = gson.fromJson(articlesRes, type)!!
    }

    val localArticles: List<CachedArticle> get() = mutableLocalArticles
    private val mutableLocalArticles = mutableListOf<CachedArticle>()
    override suspend fun requestWeather(section: String, period: Int): List<Article> {

        return apiResponse.articles.map { it.mapToDomain() }
    }

    override suspend fun storeWeather(articles: List<Article>) {
        mutableLocalArticles.addAll(articles.map { CachedArticle.fromDomain(it) })
    }

    override fun getWeather(): Flow<List<Article>> {
        return flow {
            emit(localArticles.map { it.toDomain() })
        }
    }

    override fun getArticle(id: Long): Flow<Article> {
        return flow {
            localArticles.firstOrNull()?.let { emit(it.toDomain()) }
        }
    }
}