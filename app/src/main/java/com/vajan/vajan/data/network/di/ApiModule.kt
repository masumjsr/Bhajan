package com.vajan.vajan.data.network.di

import com.google.gson.GsonBuilder
import com.vajan.vajan.data.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule
{
    @Provides
    @Singleton
    fun provideLoggingInterceptor():HttpLoggingInterceptor{
        return  HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor):OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient):Retrofit{
        return  Retrofit.Builder()
            .baseUrl("https://api.jsonbin.io/v3/b/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(client)
            .build()
    }


    @Provides
    @Singleton
    fun provideNameApi(retrofit: Retrofit): Api {
        return  retrofit.create(Api::class.java)
    }



}