package com.vajan.vajan.data.offline.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.vajan.vajan.model.Category
import com.vajan.vajan.model.Record
import kotlinx.coroutines.flow.Flow

@Dao
interface BhajanDao {
    @Upsert
    suspend fun upsertRecord(record: List<Record>)

    @Query(
        value = "Delete FROM record"
    )
    suspend fun deleteAll()

    @Transaction
    @Query(
        value = "SELECT * FROM Record"
    )
    fun getRecords(): Flow<List<Record>>

    @Transaction
    @Query(
        value = "select category from record group by category"
    )
    fun getCategory(): Flow<List<Category>>

    @Transaction
    @Query(
        value = "SELECT * FROM Record where bhajanId = :bhajanId"
    )
    fun getRecord(bhajanId:String): Flow<Record>

    @Transaction
    @Query(
        value = "SELECT * FROM Record where category = :category"
    )
    fun getRecordByCategory(category:String): Flow<List<Record>>





}