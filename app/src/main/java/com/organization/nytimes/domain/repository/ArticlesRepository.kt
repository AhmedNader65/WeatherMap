package com.organization.nytimes.domain.repository

import com.organization.nytimes.domain.model.Article
import kotlinx.coroutines.flow.Flow


interface ArticlesRepository {
    suspend fun requestArticles(section: String, period: Int): List<Article>
    suspend fun storeArticles(articles: List<Article>)
    fun getArticles(): Flow<List<Article>>
    fun getArticle(id: Long): Flow<Article>
}