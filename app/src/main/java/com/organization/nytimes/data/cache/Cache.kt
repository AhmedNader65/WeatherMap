package com.organization.nytimes.data.cache

import com.organization.nytimes.data.cache.model.CachedArticle
import com.organization.nytimes.data.cache.model.CachedArticleWithImages
import com.organization.nytimes.data.cache.model.CachedImage
import kotlinx.coroutines.flow.Flow

interface Cache {

    fun getArticles(): Flow<List<CachedArticleWithImages>>

    suspend fun storeArticles(articles: List<CachedArticle>)

    suspend fun storeImage(images: List<CachedImage>)
}