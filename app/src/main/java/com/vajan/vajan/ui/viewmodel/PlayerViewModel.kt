package com.vajan.vajan.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vajan.vajan.data.repository.BhajanRepository
import com.vajan.vajan.data.repository.UserDataRepository
import com.vajan.vajan.model.Favorite
import com.vajan.vajan.model.FavoriteRecord
import com.vajan.vajan.model.Record
import com.vajan.vajan.model.emptyFavoriteRecord
import com.vajan.vajan.ui.navigation.bhajanArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val bhajanRepository: BhajanRepository,
    private val userDataRepository: UserDataRepository,
    savedStateHandle: SavedStateHandle
):ViewModel(){

    val bhajanId = savedStateHandle.get<String>(bhajanArg)?:""
    val favoriteRecordId=userDataRepository.getUserData().map { it.favoriteRecord }

    init {
        viewModelScope.launch {
            bhajanRepository.sync()
        }
    }

    val record=bhajanRepository.getRecord(bhajanId)
        .filterNot {
            it==null ||it.bhajanId.isEmpty()
        }
        .combine(favoriteRecordId){record,favoriteId->
            FavoriteRecord(record,favoriteId.contains(record.bhajanId))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue= emptyFavoriteRecord
        )


    fun updateFavorite(bhajanId:String, isFavorite:Boolean){
        viewModelScope.launch {
            userDataRepository.toggleFavoriteBhajan(bhajanId,isFavorite)
        }
    }

}