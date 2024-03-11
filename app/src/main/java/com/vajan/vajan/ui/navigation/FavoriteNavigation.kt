package com.vajan.vajan.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.vajan.vajan.ui.screen.FavoriteScreenRoute

const val favoriteNavigationRoute = "favorite_navigation_route"

fun NavController.navigateToFavorite(navOptions: NavOptions? = null){
    popBackStack()
    this.navigate(favoriteNavigationRoute,navOptions)
}

fun NavGraphBuilder.favoriteScreen(
    onRecordClick: (String) -> Unit,
    onDrawerClick: () -> Unit
) {
    composable(favoriteNavigationRoute){
    FavoriteScreenRoute(onRecordClick=onRecordClick,onDrawerClick=onDrawerClick)
    }
}





