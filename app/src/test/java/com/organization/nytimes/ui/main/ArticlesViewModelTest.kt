package com.organization.nytimes.ui.main

import com.organization.nytimes.data.FakeRepository
import com.organization.nytimes.data.di.DataModule
import com.organization.nytimes.domain.model.Article
import com.organization.nytimes.domain.repository.ArticlesRepository
import com.organization.nytimes.domain.usecases.FetchArticles
import com.organization.nytimes.domain.usecases.GetArticle
import com.organization.nytimes.domain.usecases.GetArticles
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ArticlesViewModelTest{

    private lateinit var articleRepository: ArticlesRepository

    lateinit var viewModel: ArticlesViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup(){
        articleRepository = FakeRepository()

        viewModel = ArticlesViewModel(GetArticles(articleRepository), GetArticle(articleRepository),
            FetchArticles(articleRepository), UnconfinedTestDispatcher()
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