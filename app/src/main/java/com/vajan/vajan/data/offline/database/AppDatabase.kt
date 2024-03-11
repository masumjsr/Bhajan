package com.vajan.vajan.data.offline.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vajan.vajan.data.offline.database.dao.BhajanDao
import com.vajan.vajan.model.Favorite
import com.vajan.vajan.model.Record

@Database(
    entities = [
        Record::class
    ],
    version = 17,
    exportSchema = true
)
abstract class AppDatabase:RoomDatabase () {
    abstract fun bhajanDao(): BhajanDao

}