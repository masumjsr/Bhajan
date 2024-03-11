package com.vajan.vajan.ui.screen

import androidx.compose.foundation.layout.Box
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
import com.vajan.vajan.model.Favorite
import com.vajan.vajan.model.FavoriteRecord
import com.vajan.vajan.ui.component.SearchAppBar
import com.vajan.vajan.ui.component.VideoCard
import com.vajan.vajan.ui.component.util.SearchWidgetState
import com.vajan.vajan.ui.viewmodel.BhajanViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreenRoute(
    onRecordClick:(String)->Unit,
    onDrawerClick:()->Unit,
    viewModel: BhajanViewModel= hiltViewModel()
) {
    val records by viewModel.records.collectAsStateWithLifecycle()
    val searchWidgetState by viewModel.searchWidgetState
    val searchTextState by viewModel.searchTextState

    HomeScreen(
        onRecordClick=onRecordClick,
        records=records,
        updateFavorite = viewModel::updateFavorite,
        searchWidgetState =searchWidgetState,
        searchTextState =searchTextState,
        onTextChanged ={ text-> viewModel.updateSearchTextState(text)},
        onClose = { searchWidgetStateValue -> viewModel.updateSearchWidgetState(searchWidgetStateValue)},
        onSearchTrigger = { searchWidgetStateValue -> viewModel.updateSearchWidgetState(searchWidgetStateValue)},
        onDrawerClick = onDrawerClick
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onRecordClick:(String)->Unit,
    records: List<FavoriteRecord>,
    updateFavorite: (bhajanId:String,isFavorite:Boolean) -> Unit,

    onTextChanged: (String) -> Unit,
    onClose: (SearchWidgetState) -> Unit,
    onSearchTrigger: (SearchWidgetState) -> Unit,
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onDrawerClick:()->Unit

    ) {

    Scaffold(
        topBar = {
          when(searchWidgetState){
              SearchWidgetState.CLOSED->{
                  TopAppBar(
                      title = {

                          Text(
                              text = stringResource(id = R.string.app_name),
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
                      actions = {
                          IconButton(onClick = { onSearchTrigger (SearchWidgetState.OPENED)}) {
                              Icon(imageVector = Icons.Filled.Search, contentDescription = null)

                          }
                      }
                  )
              }
              SearchWidgetState.OPENED->{
                  SearchAppBar(
                      text = searchTextState,
                      onTextChange = onTextChanged,
                      onCloseClicked = {
                          onClose(SearchWidgetState.CLOSED)
                      },
                      onSearchClicked = {
                          onSearchTrigger(SearchWidgetState.OPENED)
                      }
                  )
              }
          }
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
fun previewHomeScreen() {
   // HomeScreen(listOf())

}