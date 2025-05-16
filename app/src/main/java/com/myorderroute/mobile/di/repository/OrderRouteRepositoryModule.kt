/*
 * OrderRouteRepositoryModule.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.di.repository

import com.myorderroute.mobile.data.repository.OrderRouteRepositoryImpl
import com.myorderroute.mobile.domain.repository.OrderRouteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class OrderRouteRepositoryModule {

    @Binds
    @Singleton
    abstract fun provideOrderRouteRepository(orderRouteRepositoryImpl: OrderRouteRepositoryImpl): OrderRouteRepository
}
