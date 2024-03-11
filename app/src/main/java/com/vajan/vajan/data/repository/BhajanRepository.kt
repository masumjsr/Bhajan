package com.vajan.vajan.data.repository

import com.vajan.vajan.data.Api
import com.vajan.vajan.data.offline.database.dao.BhajanDao
import com.vajan.vajan.model.Favorite
import com.vajan.vajan.model.toRecords
import javax.inject.Inject

class BhajanRepository @Inject constructor(
    private val api:Api,
    private val dao: BhajanDao
) {
    suspend fun sync() {
        try {
            val data = api.getRecord()
            dao.deleteAll()
            dao.upsertRecord(data.record.record.toRecords())

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getRecords() = dao.getRecords()
    fun getRecord(bhajanId: String) = dao.getRecord(bhajanId)


}
