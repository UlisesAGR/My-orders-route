/*
 * RoomModule.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
/*package com.myorderroute.mobile.di.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            Database::class.java,
            DATABASE_NAME,
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideProductDao(database: Database): ProductsDao =
        database.productDao()
}*/
