/*
 * ServicesModule.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2024. All rights reserved.
 */
package com.myorderroute.mobile.di.network

import com.myorderroute.mobile.data.network.service.OrderRouteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ServicesModule {
    @Provides
    fun provideOrderRouteService(retrofit: Retrofit): OrderRouteService =
        retrofit.create(OrderRouteService::class.java)
}
