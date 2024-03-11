package com.vajan.vajan.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "record")
data class Record(
    @PrimaryKey(autoGenerate = false)
    val bhajanId: String,
    val audio_url: String,
    val genre: String,
    val language: String,
    val length: Int,
    val lyrics: String,
    val name: String,
    val singers:String,
    val video_url: String,
    val category:String
)
val emptyRecord = Record(
    bhajanId = "",
    audio_url = "",
    genre = "",
    language = "",
    length = 0,
    lyrics = "",
    name = "",
    singers = "",
    video_url = "",
    category = ""
)
val emptyFavoriteRecord=FavoriteRecord(emptyRecord,false)

fun NetworkRecord.toRecord():Record{
    return Record(
        bhajanId = bhajanId,
        audio_url = audio_url,
        genre = genre,
        language = language,
        length = length,
        lyrics = lyrics,
        name = name,
        singers = singer.joinToString(","),
        video_url = video_url,
        category=category
    )
}

fun List<NetworkRecord>.toRecords(): List<Record>{
    return this.map { it.toRecord() }
}
