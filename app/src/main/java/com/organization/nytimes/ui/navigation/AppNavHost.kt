package com.organization.nytimes.ui.navigation

import android.os.Build
import android.os.Bundle
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.organization.nytimes.ui.main.ArticleDetailsScreen
import com.organization.nytimes.ui.main.ArticlesScreen
import com.organization.nytimes.ui.main.ArticlesViewModel
import com.organization.nytimes.ui.model.ArticleUI
import com.organization.nytimes.ui.navigation.ArticleDetails.articleKey

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AllArticles.route,
        modifier = modifier
    ) {
        composable(route = AllArticles.route) {
            ArticlesScreen(
                modifier = modifier.padding(16.dp),
                onArticleItemClicked = {
                    navController.navigateToSingleArticle(it)
                }
            )
        }
        composable(
            route = ArticleDetails.route
        ) { navBackStackEntry ->
            val article =
                if (Build.VERSION.SDK_INT >= 33) {
                    navBackStackEntry.arguments?.getSerializable(articleKey, ArticleUI::class.java)
                } else {
                    navBackStackEntry.arguments?.getSerializable(articleKey) as ArticleUI?
                }
            article?.let {
                val viewModel = hiltViewModel<ArticlesViewModel>()
                viewModel.getArticleById(it.id)
                ArticleDetailsScreen(modifier= modifier.padding(16.dp),viewModel = viewModel)
            }
        }
    }
}

private fun NavHostController.navigateToSingleArticle(articleUI: ArticleUI) {
    this.navigatee(route = ArticleDetails.route, Bundle().apply {
        this.putSerializable(articleKey, articleUI)
    })
}

fun NavHostController.navigatee(
    route: String,
    args: Bundle,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {

    val routeLink = NavDeepLinkRequest
        .Builder
        .fromUri(NavDestination.createRoute(route).toUri())
        .build()

    val deepLinkMatch = graph.matchDeepLink(routeLink)
    if (deepLinkMatch != null) {
        val destination = deepLinkMatch.destination
        val id = destination.id
        navigate(id, args, navOptions, navigatorExtras)
    } else {
        navigate(route, navOptions, navigatorExtras)
    }
}

val appScreens = listOf(AllArticles, ArticleDetails)
