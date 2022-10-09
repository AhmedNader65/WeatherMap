package com.organization.nytimes.ui.model

import com.organization.nytimes.domain.model.Article

class ArticleUI(
    val id: Long,
    val title: String,
    val thumbnail: String,
    val publishDate: String,
    val byLine: String
) {
    companion object {
        fun fromDomain(article: Article): ArticleUI {
            return ArticleUI(
                article.id,
                article.title,
                article.getSmallestImage(),
                article.published_date,
                article.byline
            )
        }
    }
}