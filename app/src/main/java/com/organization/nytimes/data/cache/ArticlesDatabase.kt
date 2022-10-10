package com.organization.nytimes.data.cache


import androidx.room.Database
import androidx.room.RoomDatabase
import com.organization.nytimes.data.cache.daos.ArticlesDao
import com.organization.nytimes.data.cache.model.CachedArticle
import com.organization.nytimes.data.cache.model.CachedImage

@Database(
    entities = [
        CachedArticle::class, CachedImage::class
    ],
    version = 2
)
abstract class ArticlesDatabase : RoomDatabase() {
    abstract fun articlesDao(): ArticlesDao
}