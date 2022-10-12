package com.organization.nytimes.ui.model

import com.organization.nytimes.domain.model.Article
import java.io.Serializable

class ArticleDetailsUI(
    val id: Long,
    val title: String,
    val abstract: String,
    val caption: String,
    val image: String,
    val publishDate: String,
    val byLine: String
) : Serializable {
    companion object {
        fun fromDomain(article: Article): ArticleDetailsUI {
            return ArticleDetailsUI(
                article.id,
                article.title,
                article.abstract,
                article.caption,
                article.getBiggestImage(),
                article.published_date,
                article.byline
            )
        }
    }
}