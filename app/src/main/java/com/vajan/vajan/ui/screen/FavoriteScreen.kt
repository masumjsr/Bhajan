package com.vajan.vajan.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vajan.vajan.model.Favorite
import com.vajan.vajan.model.FavoriteRecord
import com.vajan.vajan.ui.component.VideoCard
import com.vajan.vajan.ui.viewmodel.BhajanViewModel

@Composable
fun FavoriteScreenRoute(
    onRecordClick: (String) -> Unit,

    viewModel: BhajanViewModel = hiltViewModel()
) {
    val favoriteRecords by viewModel.favoriteChannel.collectAsStateWithLifecycle()

    FavoriteScreen(
        favoriteRecords = favoriteRecords,
        onRecordClick = onRecordClick,
        updateFavorite = viewModel::updateFavorite
    )
}

@Composable
fun FavoriteScreen(
    onRecordClick: (String) -> Unit,
    updateFavorite: (bhajanId:String,isFavorite:Boolean) -> Unit,
    favoriteRecords: List<FavoriteRecord>,
) {
    LazyColumn {
        items(favoriteRecords) { favoriteRecord ->
            VideoCard(
                FavoriteRecord(favoriteRecord.record, favoriteRecord.isFavorite),
                onRecordClick = onRecordClick,
                onFavoriteClick = updateFavorite
            )
        }
    }
}

@Preview
@Composable
fun previewFavoriteScreen() {


}