package com.vajan.vajan.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vajan.vajan.data.repository.BhajanRepository
import com.vajan.vajan.data.repository.CategoryRepository
import com.vajan.vajan.data.repository.UserDataRepository
import com.vajan.vajan.model.Favorite
import com.vajan.vajan.model.FavoriteRecord
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
class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
):ViewModel(){




    val categories=categoryRepository.getCategory()
        .filterNot { it.isEmpty() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue= listOf( )
        )


}