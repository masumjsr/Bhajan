package com.vajan.vajan.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vajan.vajan.R
import com.vajan.vajan.model.FavoriteRecord
import com.vajan.vajan.ui.component.VideoCard
import com.vajan.vajan.ui.icon.AppIcons
import com.vajan.vajan.ui.viewmodel.CategoryDetailsViewModel

@Composable
fun CategoryDetailsScreenRoute(
    onBackClick:()->Unit,
    onRecordClick:(String)->Unit,
    viewModel: CategoryDetailsViewModel= hiltViewModel()
) {
    val records by viewModel.records.collectAsStateWithLifecycle()
    val categoryName =viewModel.categoryId

    CategoryDetailsScreen(
        onBackClick=onBackClick,
        onRecordClick=onRecordClick,
        categoryName=categoryName,
        records=records,
        updateFavorite = viewModel::updateFavorite,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailsScreen(
    onRecordClick: (String) -> Unit,
    records: List<FavoriteRecord>,
    categoryName:String,
    updateFavorite: (bhajanId: String, isFavorite: Boolean) -> Unit,
    onBackClick: () -> Unit,

    ) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                    Text(
                        text = categoryName,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {

                    }) {
                       IconButton(onClick = { onBackClick.invoke() }) {
                           Icon(
                               imageVector = AppIcons.Back,
                               contentDescription = "back Icon"
                           )
                       }
                    }
                },
                actions = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                }
            )
        }
    ) {
        LazyColumn(
            modifier= Modifier.padding(it),
        ) {
            items(records) { favoriteRecord ->
                VideoCard(favoriteRecord, onRecordClick = onRecordClick, onFavoriteClick = updateFavorite)
            }
        }
    }

}

@Preview
@Composable
fun previewCategoryDetailsScreen() {
   // HomeScreen(listOf())

}