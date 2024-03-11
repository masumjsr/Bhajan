package com.vajan.vajan.model

data class NetworkRecord(
    val bhajanId: String,
    val name: String,
    val singer: List<String>,
    val video_url: String,
    val audio_url: String,
    val lyrics: String,
    val language: String,
    val genre: String,
    val length: Int,
    val category: String
)