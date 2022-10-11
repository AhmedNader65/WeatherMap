package com.organization.nytimes.data.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.organization.nytimes.domain.model.Article


@Entity(tableName = "articles")
data class CachedArticle(
    @PrimaryKey
    var articleId: Long = 0L,
    @ColumnInfo(name = "url") var url: String = "",
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "abstract") var abstract: String = "",
    @ColumnInfo(name = "byline") var byline: String = "",
    @ColumnInfo(name = "caption") var caption: String = "",
    @ColumnInfo(name = "published_date") var published_date: String = "",
    @ColumnInfo(name = "updated") var updated: String = "",
) {
    companion object {
        fun fromDomain(article: Article): CachedArticle {
            return CachedArticle(
                article.id,
                article.url,
                article.title,
                article.abstract,
                article.byline,
                article.caption,
                article.published_date,
                article.updated,
            )
        }

    }
}

fun CachedArticle.toDomain(): Article {

    return Article(
        articleId,
        url,
        published_date,
        updated,
        byline,
        title,
        abstract,
        caption,
        listOf()
    )
}
