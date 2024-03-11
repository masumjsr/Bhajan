package com.vajan.vajan.data.offline.database.di

import com.vajan.vajan.data.offline.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object  DaosModule {

    @Provides
    fun provideBhajanDao(
        database: AppDatabase
    )=database.bhajanDao()





}