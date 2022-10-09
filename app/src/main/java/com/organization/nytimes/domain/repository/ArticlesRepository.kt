package com.organization.nytimes.domain.repository

import com.organization.nytimes.domain.model.Article


interface ArticlesRepository {
    suspend fun requestArticles(section:String, period: Int): List<Article>

}