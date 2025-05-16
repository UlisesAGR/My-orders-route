/*
 * OrderRouteSourceModule.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.di.source

import com.myorderroute.mobile.data.source.OrderRouteSource
import com.myorderroute.mobile.data.source.OrderRouteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class OrderRouteSourceModule {

    @Binds
    @Singleton
    abstract fun provideOrderRouteSource(orderRouteSourceImpl: OrderRouteSourceImpl): OrderRouteSource
}
