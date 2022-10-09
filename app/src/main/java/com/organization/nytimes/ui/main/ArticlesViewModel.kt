package com.organization.nytimes.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.organization.nytimes.domain.usecases.FetchArticles
import com.organization.nytimes.domain.usecases.GetArticles
import com.organization.nytimes.ui.model.ArticleUI
import com.organization.nytimes.utils.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val getArticles: GetArticles,
    private val fetchArticles: FetchArticles
) : ViewModel() {

    private val _articles = MutableStateFlow<List<ArticleUI>?>(null)
    val articles: StateFlow<List<ArticleUI>?> =
        _articles.asStateFlow()

    init {
        viewModelScope.launch {
            fetchArticles("all-sections", 7)
            subscribeToArticlesUpdates()
        }
    }

    private fun subscribeToArticlesUpdates() {
        viewModelScope.launch {
            getArticles()
                .map { articles ->
                    articles.map {
                        ArticleUI.fromDomain(it)
                    }
                }.collect{
                    onNewArticlesList(it)
                }
        }
    }

    private suspend fun onNewArticlesList(articles: List<ArticleUI>) {
        Logger.d("Got more Articles $articles")
        _articles.emit(articles)
    }
}