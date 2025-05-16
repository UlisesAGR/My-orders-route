/*
 * OrderRouteSourceImpl.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.data.source

import com.myorderroute.mobile.data.network.service.OrderRouteService
import com.myorderroute.mobile.domain.mapper.toDomain
import com.myorderroute.mobile.domain.model.CoordinatesModel
import com.myorderroute.mobile.util.network.Resource
import com.myorderroute.mobile.util.network.toResult
import javax.inject.Inject

class OrderRouteSourceImpl @Inject constructor(
    private val orderRouteService: OrderRouteService,
) : OrderRouteSource {

    override suspend fun getCoordinates(): Resource<List<CoordinatesModel>> =
        orderRouteService.getCoordinates().toResult {
            data.map { list -> list.toDomain() }
        }
}
