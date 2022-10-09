package com.organization.nytimes.data.cache.daos

import androidx.room.*
import com.organization.nytimes.data.cache.model.CachedArticle
import com.organization.nytimes.data.cache.model.CachedArticleWithImages
import com.organization.nytimes.data.cache.model.CachedImage
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ArticlesDao {

    @Transaction
    @Query("SELECT * FROM articles ORDER BY articleId DESC")
    abstract fun getAllArticles(): Flow<List<CachedArticleWithImages>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertArticle(vararg article: CachedArticle)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertImage(vararg image: CachedImage)
}