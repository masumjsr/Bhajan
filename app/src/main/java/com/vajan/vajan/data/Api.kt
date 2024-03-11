package com.vajan.vajan.data

import com.vajan.vajan.model.Bhajan
import retrofit2.http.GET


interface Api {

    @GET("65eab7f8266cfc3fde951349")
    suspend fun getRecord(): Bhajan


}