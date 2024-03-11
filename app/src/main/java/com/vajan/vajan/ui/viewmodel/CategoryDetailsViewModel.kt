package com.vajan.vajan.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vajan.vajan.data.repository.BhajanRepository
import com.vajan.vajan.data.repository.CategoryRepository
import com.vajan.vajan.data.repository.UserDataRepository
import com.vajan.vajan.model.Favorite
import com.vajan.vajan.model.FavoriteRecord
import com.vajan.vajan.ui.navigation.categoryIdArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailsViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val userDataRepository: UserDataRepository,
    savedStateHandle: SavedStateHandle
):ViewModel(){


    val categoryId=savedStateHandle.get<String>(categoryIdArg)?:""
    private val favoriteRecord= userDataRepository.getUserData().map { it.favoriteRecord }

    val records=categoryRepository.getRecordByCategory(categoryId)
        .filterNot { it.isEmpty() }
        .combine(favoriteRecord) { bhajans, favoriteRecord ->
            bhajans.map { bhajan ->
                FavoriteRecord(bhajan, favoriteRecord.contains(bhajan.bhajanId))
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