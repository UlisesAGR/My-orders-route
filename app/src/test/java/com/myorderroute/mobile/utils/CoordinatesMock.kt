/*
 * CoordinatesMock.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.utils

import com.myorderroute.mobile.data.network.model.CoordinatesDataResponse
import com.myorderroute.mobile.data.network.model.CoordinatesResponse
import com.myorderroute.mobile.domain.model.CoordinatesModel
import retrofit2.Response

object CoordinatesMock {

    val coordinatesModelList: List<CoordinatesModel> =
        listOf(
            CoordinatesModel(
                name = "Location 1",
                latitude = 19.432608,
                longitude = -99.133209,
            ),
            CoordinatesModel(
                name = "Location 2",
                latitude = 19.432608,
                longitude = -99.133209,
            ),
        )

    private val coordinatesResponseList: List<CoordinatesResponse> =
        listOf(
            CoordinatesResponse(
                name = "Location 1",
                latitude = 19.432608,
                longitude = -99.133209,
            ),
            CoordinatesResponse(
                name = "Location 2",
                latitude = 19.432608,
                longitude = -99.133209,
            ),
        )

    val coordinatesDataResponse: Response<CoordinatesDataResponse> =
        Response.success(CoordinatesDataResponse(coordinatesResponseList))
}
