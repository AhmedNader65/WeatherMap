package com.organization.weathermap.ui.main

import com.organization.weathermap.data.FakeRepository
import com.organization.weathermap.domain.model.Article
import com.organization.weathermap.domain.repository.ArticlesRepository
import com.organization.weathermap.domain.usecases.FetchWeather
import com.organization.weathermap.domain.usecases.GetWeather
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ArticlesViewModelTest{

    private lateinit var articleRepository: ArticlesRepository

    lateinit var viewModel: WeatherViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup(){
        articleRepository = FakeRepository()

        viewModel = WeatherViewModel(GetArticles(articleRepository), GetWeather(articleRepository),
            FetchWeather(articleRepository), UnconfinedTestDispatcher()
        )
    }

    @Test
    fun testGetArticleById_idExists_returnsArticle() = runBlocking{
        // Given
        articleRepository.requestArticles("",7)

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
        viewModel.getArticleById(article.id)

        // Then
        assertEquals(article.title, viewModel.article.first()?.title)
    }

    @Test
    fun testGetArticleById_idNotExists_returnsNull() = runBlocking{
        // Given
        articleRepository.requestArticles("",7)

        // When
        viewModel.getArticleById(45)

        // Then
        assertEquals(null, viewModel.article.first()?.title)
    }


}