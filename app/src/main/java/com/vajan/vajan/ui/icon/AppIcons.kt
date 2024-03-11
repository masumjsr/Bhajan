 package com.vajan.vajan.ui.icon
import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.ui.graphics.vector.ImageVector

object AppIcons {
    val Back = Icons.AutoMirrored.Rounded.ArrowBack
    val favorite = Icons.Filled.Favorite
    val favoriteBorder = Icons.Outlined.FavoriteBorder
    val home = Icons.Filled.DateRange
    val homeBorder = Icons.Outlined.DateRange
    val category= Icons.Filled.Category
    val categoryBorder = Icons.Outlined.Category
    val settings = Icons.Filled.Settings
    val settingsBorder = Icons.Outlined.Settings



}
sealed class Icon{
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}

