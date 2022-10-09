package com.organization.nytimes.domain.usecases

import com.organization.nytimes.domain.repository.ArticlesRepository
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

class GetArticles @Inject constructor(private val articlesRepository: ArticlesRepository) {

    operator fun invoke() = articlesRepository.getArticles()
        .filter { it.isNotEmpty() }
}