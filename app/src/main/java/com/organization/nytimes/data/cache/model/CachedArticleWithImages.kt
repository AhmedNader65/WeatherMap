package com.organization.nytimes.data.cache.model

import androidx.room.Embedded
import androidx.room.Relation
import com.organization.nytimes.domain.model.Article

data class CachedArticleWithImages(
    @Embedded val cachedArticle: CachedArticle,
    @Relation(
        parentColumn = "articleId",
        entityColumn = "articleId"
    )
    val cachedImages: List<CachedImage>
)

fun CachedArticleWithImages.toDomain(): Article {

    return Article(
        cachedArticle.articleId,
        cachedArticle.url,
        cachedArticle.published_date,
        cachedArticle.updated,
        cachedArticle.byline,
        cachedArticle.title,
        cachedArticle.abstract,
        cachedArticle.caption,
        cachedImages.map { it.toDomain() }
    )
}