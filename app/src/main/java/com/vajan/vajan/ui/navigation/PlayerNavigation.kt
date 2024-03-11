package com.vajan.vajan.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vajan.vajan.model.FavoriteRecord
import com.vajan.vajan.ui.screen.PlayerScreenRoute

const val playerScreenRoute="player_screen"
const val bhajanArg="bhajanArg"

fun NavGraphBuilder.playerScreen(
    onBackClick: () -> Unit
) {
    composable(route = "$playerScreenRoute/{$bhajanArg}",
        arguments = listOf(
            navArgument(bhajanArg){type= NavType.StringType}
        )
    ) {
        PlayerScreenRoute(onBackClick = onBackClick)
    }
}

fun NavController.navigateToPlayerScreen(bhajanId:String) {
    navigate("$playerScreenRoute/${bhajanId}")
}