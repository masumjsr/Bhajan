package com.vajan.vajan.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.vajan.vajan.ui.screen.HomeScreenRoute

const val homeNavigationRoute = "home_navigation_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null){
    popBackStack()
    this.navigate(homeNavigationRoute,navOptions)
}

fun NavGraphBuilder.homeScreen(
    onRecordClick:(String)->Unit,
    onDrawerClick:()->Unit
) {
    composable(homeNavigationRoute){
    HomeScreenRoute(onRecordClick,onDrawerClick)
    }
}





