package com.vajan.vajan.ui.navigation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vajan.vajan.ui.screen.CategoryDetailsScreenRoute
import com.vajan.vajan.ui.screen.CategoryScreenRoute

const val categoryNavigationRoute = "category_navigation_route"
const val categoryDetailsNavigationRoute="category_details_navigation_route"
const val categoryIdArg="category_name"

fun NavGraphBuilder.categoryScreen(
    onClick: (categoryId: String) -> Unit,
    onDrawerClick: () -> Unit
) {
    composable(categoryNavigationRoute){
    CategoryScreenRoute(onClick=onClick,onDrawerClick=onDrawerClick)
    }
}


fun NavController.navigateToCategory(navOptions: NavOptions? = null){
    popBackStack()
    this.navigate(categoryNavigationRoute,navOptions)
}

fun NavGraphBuilder.categoryDetailsScreen(
    onBackClick: () -> Unit,
    onRecordClick:(String)->Unit

) {
    composable(route = "$categoryDetailsNavigationRoute/{$categoryIdArg}",
        arguments = listOf(
            navArgument(categoryIdArg){type= NavType.StringType}
        )
    ) {
        CategoryDetailsScreenRoute(onBackClick = onBackClick,onRecordClick = onRecordClick)
    }
}

fun NavController.navigateToCategoryDetails(categoryId:String) {
    navigate("$categoryDetailsNavigationRoute/${Uri.encode(categoryId)}")
}


