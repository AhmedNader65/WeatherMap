package com.organization.weathermap.domain.usecases

import com.organization.weathermap.data.FakeRepository
import com.organization.weathermap.domain.model.Article
import com.organization.weathermap.domain.repository.ArticlesRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UseCasesTest{

    private lateinit var articleRepository: ArticlesRepository

    private lateinit var getArticlesUseCase : GetArticles
    private lateinit var fetchArticlesUseCase : FetchWeather
    private lateinit var getArticleUseCase : GetWeather


    @Before
    fun setUp() {
        articleRepository = FakeRepository()
        fetchArticlesUseCase = FetchWeather(articleRepository)
        getArticlesUseCase = GetArticles(articleRepository)
        getArticleUseCase = GetWeather(articleRepository)
    }

    @Test
    fun testFetchingAndGettingArticles() = runBlocking {
        // Given
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

        // When
        fetchArticlesUseCase("",7)
        val result = getArticlesUseCase().first()

        // Then
        assertEquals(article.title,result.firstOrNull()?.title )
    }

    @Test
    fun testGetArticleById() = runBlocking {
        // Given
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

        // When
        fetchArticlesUseCase("",7)
        val result = getArticleUseCase(100000008485792)

        // Then
        assertEquals(article.title,result.first().title )
    }

}