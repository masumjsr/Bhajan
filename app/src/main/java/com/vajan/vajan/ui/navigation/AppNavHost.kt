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
    onBackClick :()->Unit,
    onDrawerClick:()->Unit

) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
         ) {

        homeScreen(onRecordClick = navController::navigateToPlayerScreen,onDrawerClick=onDrawerClick)
        categoryScreen(onClick = navController::navigateToCategoryDetails,onDrawerClick=onDrawerClick)
        favoriteScreen(onRecordClick = navController::navigateToPlayerScreen,onDrawerClick=onDrawerClick)
        settingScreen(onDrawerClick=onDrawerClick)
        playerScreen(onBackClick)
        categoryDetailsScreen (onBackClick, onRecordClick = navController::navigateToPlayerScreen)


    }

}