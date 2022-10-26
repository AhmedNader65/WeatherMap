package com.organization.weathermap.ui.model

import com.organization.weathermap.domain.model.Article
import org.junit.Assert.*
import org.junit.Test

class ArticleDetailsUITest {

    @Test
    fun testMappingFromDomain_resultingDetailsUIObject() {
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
        val uiArticleDetailsUI = ArticleDetailsUI.fromDomain(article = article)

        // THEN

        assertEquals(article.id , uiArticleDetailsUI.id )
        assertEquals(article.abstract , uiArticleDetailsUI.abstract )
        assertEquals(article.title , uiArticleDetailsUI.title )
        assertEquals(article.caption , uiArticleDetailsUI.caption )
        assertEquals(article.byline , uiArticleDetailsUI.byLine )
        assertEquals(article.published_date , uiArticleDetailsUI.publishDate )
    }
}