package com.organization.nytimes.ui.model

import com.organization.nytimes.data.api.model.mapToDomain
import com.organization.nytimes.domain.model.Article
import org.junit.Assert.*
import org.junit.Test

class ArticleUITest {

    @Test
    fun testMappingFromDomain_resultingArticleUIObject() {
        // GIVEN
        val article = Article(
            100000008485792,
            "https://www.nytimes.com/2022/09/14/climate/patagonia-climate-philanthropy-chouinard.html",
            "2022-09-14",
            "2022-09-21 18:05:13",
            "By David Gelles",
            "Billionaire No More: Patagonia Founder Gives Away the Company",
            "Yvon Chouinard has forfeited ownership of the company he founded 49 years ago. The profits will now be used to fight climate change.",
            "caption",
            listOf()
        )
        // WHEN
        val uiArticleUI = ArticleUI.fromDomain(article = article)

        // THEN

        assertEquals(article.id , uiArticleUI.id )
        assertEquals(article.title , uiArticleUI.title )
        assertEquals(article.byline , uiArticleUI.byLine )
        assertEquals(article.published_date , uiArticleUI.publishDate )
    }
}