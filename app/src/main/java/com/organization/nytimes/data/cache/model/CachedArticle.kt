package com.organization.nytimes.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.organization.nytimes.domain.model.Article


@Entity(tableName = "articles")
class CachedArticle(
    @PrimaryKey
    val articleId: Long,
    val url: String,
    val published_date: String,
    val updated: String,
    val byline: String,
    val title: String,
    val abstract: String,
) {
    companion object {
        fun fromDomain(article: Article): CachedArticle {
            return CachedArticle(
                article.id,
                article.url,
                article.published_date,
                article.updated,
                article.byline,
                article.title,
                article.abstract,
            )
        }
    }
}
