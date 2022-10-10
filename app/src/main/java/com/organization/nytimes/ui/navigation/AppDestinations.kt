package com.organization.nytimes.ui.navigation

interface AppDestinations {
    val title: String
    val route: String
}

object AllArticles : AppDestinations {
    override val title = "NY Times"
    override val route = "articles"
}

object ArticleDetails : AppDestinations {
    override val title = "Details"
    override val route = "article"
    const val articleKey = "article_argument"
}

