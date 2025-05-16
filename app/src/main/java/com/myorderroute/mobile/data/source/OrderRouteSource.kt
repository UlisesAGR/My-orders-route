/*
 * OrderRouteSource.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.data.source

import com.myorderroute.mobile.domain.model.CoordinatesModel
import com.myorderroute.mobile.util.network.Resource

interface OrderRouteSource {
    suspend fun getCoordinates(): Resource<List<CoordinatesModel>>
}
