package com.vajan.vajan.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vajan.vajan.data.repository.BhajanRepository
import com.vajan.vajan.data.repository.UserDataRepository
import com.vajan.vajan.model.Favorite
import com.vajan.vajan.model.FavoriteRecord
import com.vajan.vajan.ui.component.util.SearchWidgetState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BhajanViewModel @Inject constructor(
    private val bhajanRepository: BhajanRepository,
    private val userDataRepository: UserDataRepository
):ViewModel(){

    init {
        viewModelScope.launch {
            bhajanRepository.sync()
        }
    }


    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState
    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState
    private val _searchText = MutableStateFlow(String())
    val searchText=_searchText.asStateFlow()

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
        _searchText.value = newValue
    }



    private val favoriteRecord= userDataRepository.getUserData().map { it.favoriteRecord }

    val records=bhajanRepository.getRecords()
        .filterNot { it.isEmpty() }
        .combine(searchText){records, searchText ->
            records.filter {
                it.name.contains(searchText,true)||it.singers.contains(searchText,true)
            }

        }
        .combine(favoriteRecord) { records, favoriteRecord ->
            records.map { bhajan ->
                FavoriteRecord(bhajan, favoriteRecord.contains(bhajan.bhajanId))
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue= listOf( )
        )

    val favoriteChannel =bhajanRepository.getRecords()
        .filterNot { it.isEmpty() }
        .combine(favoriteRecord) { bhajans, favoriteRecord ->
            bhajans.filter {
                favoriteRecord.contains(it.bhajanId)
            }.map {
                FavoriteRecord(it, true)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue= listOf( )
        )

    fun updateFavorite(bhajanId:String, isFavorite:Boolean){
        viewModelScope.launch {
           userDataRepository.toggleFavoriteBhajan(bhajanId, isFavorite)
        }
    }


}