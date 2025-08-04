package com.rustagi.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.rustagi.newsapp.domain.model.Article
import com.rustagi.newsapp.presentation.feature.newsfeed.screens.newsFeedDetails.NewsFeedDetailsScreen
import com.rustagi.newsapp.presentation.feature.newsfeed.screens.newsFeedList.NewsFeedListScreen
import kotlin.reflect.typeOf


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.NewsList) {
        composable<Screens.NewsList> {
            NewsFeedListScreen(
                onArticleClick = { article ->
                    navController.navigate(Screens.NewsFeedDetails(article))
                }
            )
        }

        composable<Screens.NewsFeedDetails>(
            typeMap = mapOf(typeOf<Article>() to ArticleNavType)
        ) { backStackEntry ->
            val route = backStackEntry.toRoute<Screens.NewsFeedDetails>()
            NewsFeedDetailsScreen(article = route.article)
        }
    }
}