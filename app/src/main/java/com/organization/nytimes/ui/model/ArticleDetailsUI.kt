package com.organization.nytimes.ui.model

import android.os.Parcelable
import com.organization.nytimes.domain.model.Article
import kotlinx.android.parcel.Parcelize

@Parcelize
class ArticleDetailsUI(
    val id: Long,
    val title: String,
    val abstract: String,
    val caption: String,
    val image: String,
    val publishDate: String,
    val byLine: String
) : Parcelable {
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