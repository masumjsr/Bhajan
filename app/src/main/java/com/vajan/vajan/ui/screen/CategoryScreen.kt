package com.vajan.vajan.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vajan.vajan.model.Category
import com.vajan.vajan.ui.component.CategoryCard
import com.vajan.vajan.ui.viewmodel.CategoryViewModel

@Composable
fun CategoryScreenRoute(
    onClick:(categoryId:String)->Unit,
    categoryViewModel: CategoryViewModel = hiltViewModel()
) {
    val categories by categoryViewModel.categories.collectAsStateWithLifecycle()

    CategoryScreen(categories,onClick=onClick)
}

@Composable
fun CategoryScreen(categories: List<Category>,onClick:(categoryId:String)->Unit) {
    Scaffold { paddingValues ->
        LazyVerticalGrid(
            modifier = Modifier.padding(paddingValues), columns = GridCells.Adaptive(150.dp)
        ) {
            items(categories) {
                CategoryCard(category = it,onClick = onClick)
            }
        }

    }

}

@Preview
@Composable
fun previewCategoryScreen() {
    CategoryScreen(listOf(),{})

}