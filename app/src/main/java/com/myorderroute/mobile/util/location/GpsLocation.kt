/*
 * GpsLocation.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.util.location

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import com.myorderroute.mobile.domain.model.CoordinatesModel
import com.myorderroute.mobile.util.Constants
import com.myorderroute.mobile.util.Constants.DEFAULT_LOCATION
import com.myorderroute.mobile.util.log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GpsLocation @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    fun getLocationGPS(onFinish: (CoordinatesModel) -> Unit) {
        tryGetLocationWithPriority(
            onFinish = onFinish,
            priority = LocationSearchPriority.HIGH,
            onFailure = {
                getLocationGPSLow(onFinish)
            }
        )
    }

    private fun getLocationGPSLow(onFinish: (CoordinatesModel) -> Unit) {
        tryGetLocationWithPriority(
            onFinish,
            LocationSearchPriority.LOW,
            onFailure = {
                onFinish(CoordinatesModel(name = "", DEFAULT_LOCATION, DEFAULT_LOCATION))
            },
        )
    }

    private fun tryGetLocationWithPriority(
        onFinish: (CoordinatesModel) -> Unit,
        priority: LocationSearchPriority,
        onFailure: () -> Unit,
    ) {
        if (hasLocationPermissions()) {
            val locationRequest = CurrentLocationRequest.Builder()
                .setDurationMillis(priority.timeInMilli)
                .setPriority(priority.type)
                .build()

            val cancellationToken = object : CancellationToken() {
                override fun onCanceledRequested(p0: OnTokenCanceledListener): CancellationToken =
                    CancellationTokenSource().token

                override fun isCancellationRequested(): Boolean =
                    false
            }

            try {
                fusedLocationClient
                    .getCurrentLocation(locationRequest, cancellationToken)
                    .addOnSuccessListener { location ->
                        location?.let {
                            onFinish(
                                CoordinatesModel(
                                    name = "",
                                    latitude = location.latitude,
                                    longitude = location.longitude,
                                )
                            )
                        } ?: run {
                            onFailure()
                        }
                    }
                    .addOnFailureListener { error ->
                        log(className = this@GpsLocation, message = error.message)
                        onFailure()
                    }
            } catch (error: SecurityException) {
                log(className = this@GpsLocation, message = error.message)
                onFailure()
            }
        } else {
            onFailure()
        }
    }

    private fun hasLocationPermissions(): Boolean =
        ContextCompat.checkSelfPermission(
            context,
            Constants.ACCESS_FINE_LOCATION_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    context,
                    Constants.ACCESS_COARSE_LOCATION_PERMISSION
                ) == PackageManager.PERMISSION_GRANTED
}
