package com.organization.nytimes.data.cache

import com.organization.nytimes.data.cache.daos.ArticlesDao
import com.organization.nytimes.data.cache.model.CachedArticle
import com.organization.nytimes.data.cache.model.CachedArticleWithImages
import com.organization.nytimes.data.cache.model.CachedImage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomCache @Inject constructor(
    private val articlesDao: ArticlesDao,
) : Cache {
    override fun getArticles(): Flow<List<CachedArticleWithImages>> = articlesDao.getAllArticles()

    override suspend fun storeArticles(articles: List<CachedArticle>) =
        articlesDao.insertArticle(*articles.toTypedArray())

    override suspend fun storeImage(images: List<CachedImage>) =
        articlesDao.insertImage(*images.toTypedArray())
}