/*
 * OrderRouteRepository.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.domain.repository

import com.myorderroute.mobile.domain.model.CoordinatesModel
import com.myorderroute.mobile.util.network.Resource

interface OrderRouteRepository {
    suspend fun getCoordinates(): Resource<List<CoordinatesModel>>
}
