/*
 * OrderRouteUiState.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.ui.viewModel

import com.myorderroute.mobile.domain.model.CoordinatesModel

data class OrderRouteUiState(
    val isLoading: Boolean = true,
    val currentLocation: CoordinatesModel? = null,
    val pendingLocations: List<CoordinatesModel> = emptyList(),
    val nextLocation: CoordinatesModel? = null,
)
