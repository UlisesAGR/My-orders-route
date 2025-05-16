/*
 * OrderRouteRepositoryImpl.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.data.repository

import com.myorderroute.mobile.data.source.OrderRouteSource
import com.myorderroute.mobile.domain.model.CoordinatesModel
import com.myorderroute.mobile.domain.repository.OrderRouteRepository
import com.myorderroute.mobile.util.network.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OrderRouteRepositoryImpl @Inject constructor(
    private val orderRouteSource: OrderRouteSource,
    private val dispatcher: CoroutineDispatcher,
) : OrderRouteRepository {

    override suspend fun getCoordinates(): Resource<List<CoordinatesModel>> = withContext(dispatcher) {
        orderRouteSource.getCoordinates()
    }
}
