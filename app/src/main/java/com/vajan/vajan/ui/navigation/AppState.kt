package com.vajan.vajan.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navHostController: NavHostController = rememberNavController(),
) : AppState {
    return remember(navHostController,coroutineScope){
        AppState(navHostController, coroutineScope)
    }
}

@Stable
class AppState (
    val navHostController: NavHostController,
    val coroutineScope: CoroutineScope
        ){
    val currentDestination: NavDestination?
    @Composable  get() = navHostController
        .currentBackStackEntryAsState().value?.destination

    val state   @Composable get()=(currentDestination?.route?.contains("{")?:true||currentDestination?.route?.contains("login")?:true)


    val topLevelDestination : List<TopLevelDestination> = TopLevelDestination.values().asList()

    fun onBackClick(){
        navHostController.popBackStack()
    }

    fun navigateToLevelDestination(topLevelDestination: TopLevelDestination){
        val topLevelNavOptions =  navOptions {
            popUpTo(navHostController.graph.findStartDestination().id){
                saveState=true
            }
            launchSingleTop= true
            restoreState = true
        }

        when(topLevelDestination){
            TopLevelDestination.HOME -> navHostController.navigateToHome(topLevelNavOptions)
            TopLevelDestination.CATEGORY -> navHostController.navigateToCategory(topLevelNavOptions)
            TopLevelDestination.FAVORITE -> navHostController.navigateToFavorite(topLevelNavOptions)
            TopLevelDestination.SETTINGS -> navHostController.navigateToSetting(topLevelNavOptions)
         }
    }
}
fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
