/*
 * GeoUtils.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.util.location

import android.location.Location
import com.myorderroute.mobile.domain.model.CoordinatesModel
import com.myorderroute.mobile.domain.state.OrderStatus

fun getNearestLocation(
    currentLocation: CoordinatesModel?,
    pendingLocations: List<CoordinatesModel>,
): CoordinatesModel? =
    currentLocation?.let {
        pendingLocations
            .filter { it.status == OrderStatus.PENDING }
            .minByOrNull { location -> calculateDistance(currentLocation, location) }
    }

private fun calculateDistance(
    from: CoordinatesModel,
    to: CoordinatesModel,
): Float {
    val result = FloatArray(1)
    Location.distanceBetween(from.latitude, from.longitude, to.latitude, to.longitude, result)
    return result[0]
}

fun markNextLocationAsCompleted(
    nextLocation: CoordinatesModel?,
    pendingLocations: List<CoordinatesModel>
): List<CoordinatesModel> =
    pendingLocations.map { location ->
        if (location == nextLocation) location.copy(status = OrderStatus.COMPLETED) else location
    }
