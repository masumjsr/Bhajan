package com.vajan.vajan.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.vajan.vajan.ui.screen.SettingScreenRoute

const val settingNavigationRoute = "settings_navigation_route"

fun NavController.navigateToSetting(navOptions: NavOptions? = null){
    popBackStack()
    this.navigate(settingNavigationRoute,navOptions)
}

fun NavGraphBuilder.settingScreen() {
    composable(settingNavigationRoute){
    SettingScreenRoute()
    }
}





