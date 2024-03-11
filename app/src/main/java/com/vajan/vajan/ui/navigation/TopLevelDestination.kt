package com.vajan.vajan.ui.navigation

import com.vajan.vajan.ui.icon.Icon
import com.vajan.vajan.ui.icon.AppIcons


enum class TopLevelDestination(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    val titleTextId: String,
){

    HOME(
        selectedIcon = Icon.ImageVectorIcon(AppIcons.home),
        unselectedIcon = Icon.ImageVectorIcon(AppIcons.homeBorder),
        titleTextId = "Home"
    ),
    FAVORITE(
        selectedIcon = Icon.ImageVectorIcon(AppIcons.favorite),
        unselectedIcon = Icon.ImageVectorIcon(AppIcons.favoriteBorder),
        titleTextId = "Favorite"
    ),

    CATEGORY(
        selectedIcon = Icon.ImageVectorIcon(AppIcons.category),
        unselectedIcon = Icon.ImageVectorIcon(AppIcons.categoryBorder),
        titleTextId = "Category"
    ),
    SETTINGS(
        selectedIcon = Icon.ImageVectorIcon(AppIcons.settings),
        unselectedIcon = Icon.ImageVectorIcon(AppIcons.settingsBorder),
        titleTextId = "Settings"
    ),



}