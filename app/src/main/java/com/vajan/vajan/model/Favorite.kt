package com.vajan.vajan.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity("favorite")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val bhajanId:String,
    @ColumnInfo(name = "isFavorite", defaultValue ="false")
    val isFavorite:Boolean=false
)

data class  FavoriteRecord(
  val record:Record,
  val isFavorite: Boolean)

