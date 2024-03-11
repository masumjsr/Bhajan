package com.vajan.vajan.ui.screen

import androidx.compose.foundation.layout.Column
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
import com.vajan.vajan.ui.component.util.SearchWidgetState
import com.vajan.vajan.ui.viewmodel.BhajanViewModel

@Composable
fun FavoriteScreenRoute(
    onRecordClick: (String) -> Unit,

    viewModel: BhajanViewModel = hiltViewModel(),
    onDrawerClick: () -> Unit
) {
    val favoriteRecords by viewModel.favoriteChannel.collectAsStateWithLifecycle()

    FavoriteScreen(
        favoriteRecords = favoriteRecords,
        onRecordClick = onRecordClick,
        onDrawerClick = onDrawerClick,
        updateFavorite = viewModel::updateFavorite
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    onRecordClick: (String) -> Unit,
    updateFavorite: (bhajanId: String, isFavorite: Boolean) -> Unit,
    favoriteRecords: List<FavoriteRecord>,
    onDrawerClick: () -> Unit,
) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {

                    Text(
                        text = "Favorite",
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onDrawerClick.invoke()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "menu icon"
                        )
                    }
                },
            )
        }
    ){
        LazyColumn(
            modifier= Modifier.padding(it),
        ) {
            items(favoriteRecords) { favoriteRecord ->
                VideoCard(
                    FavoriteRecord(favoriteRecord.record, favoriteRecord.isFavorite),
                    onRecordClick = onRecordClick,
                    onFavoriteClick = updateFavorite
                )
            }
        }
    }
}

@Preview
@Composable
fun previewFavoriteScreen() {


}