package com.vajan.vajan.data.repository

import com.vajan.vajan.datastore.PreferenceDataSource
import javax.inject.Inject

class UserDataRepository @Inject constructor(
    private val preferenceDataSource: PreferenceDataSource
) {

    fun getUserData() = preferenceDataSource.userData
    suspend fun toggleFavoriteBhajan(id: String,favorite:Boolean) = preferenceDataSource.toggleRecordResourceFavorite(id,favorite)
}