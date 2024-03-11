package com.vajan.vajan.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import com.bhajan.bhajan.datastore.UserPreferences
import com.bhajan.bhajan.datastore.copy
import com.vajan.vajan.model.UserData
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class PreferenceDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>

) {
    val userData=userPreferences.data.map {
        UserData(
            favoriteRecord = it.favoriteBhajanIdsMap.keys
        )
    }

    suspend fun toggleRecordResourceFavorite(bhajanId: String, favorite: Boolean){
        try {
            userPreferences.updateData {
                it.copy {
                    if(favorite){
                        favoriteBhajanIds.put(bhajanId, true)
                    }
                    else  {
                        favoriteBhajanIds.remove(bhajanId)
                    }
                }
            }
        } catch (ioException: IOException){
            Log.e("123321","failed to update user preferences", ioException)
        }
    }
}