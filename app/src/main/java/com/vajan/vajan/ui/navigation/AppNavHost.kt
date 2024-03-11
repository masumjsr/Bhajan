package com.vajan.vajan.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier =Modifier,
    startDestination:String = homeNavigationRoute,
    onBackClick :()->Unit

) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
         ) {

        homeScreen(onRecordClick = navController::navigateToPlayerScreen)
        categoryScreen(onClick = navController::navigateToCategoryDetails)
        favoriteScreen(onRecordClick = navController::navigateToPlayerScreen)
        settingScreen()
        playerScreen(onBackClick)
        categoryDetailsScreen (onBackClick, onRecordClick = navController::navigateToPlayerScreen)


    }

}