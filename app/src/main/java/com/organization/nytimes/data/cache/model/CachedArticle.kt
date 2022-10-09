package com.organization.nytimes.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.organization.nytimes.domain.model.Article


@Entity(tableName = "articles")
data class CachedArticle(
    @PrimaryKey
    var articleId: Long = 0L,
    var url: String = "",
    var published_date: String = "",
    var updated: String = "",
    var byline: String = "",
    var title: String = "",
    var abstract: String = "",
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
