/*
 * OrderRouteService.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.data.network.service

import com.myorderroute.mobile.BuildConfig.LOCATIONS_ENDPOINT
import com.myorderroute.mobile.data.network.model.CoordinatesDataResponse
import retrofit2.Response
import retrofit2.http.GET

interface OrderRouteService {

    @GET(LOCATIONS_ENDPOINT)
    suspend fun getCoordinates(): Response<CoordinatesDataResponse>
}
