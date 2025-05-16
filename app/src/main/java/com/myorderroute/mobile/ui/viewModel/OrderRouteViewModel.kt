/*
 * OrderRouteViewModel.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.ui.viewModel

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myorderroute.mobile.domain.model.CoordinatesModel
import com.myorderroute.mobile.domain.state.OrderStatus
import com.myorderroute.mobile.domain.usecase.GetCoordinatesUseCase
import com.myorderroute.mobile.util.location.GpsLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderRouteViewModel @Inject constructor(
    val getCoordinatesUseCase: GetCoordinatesUseCase,
    private val gpsLocation: GpsLocation,
) : ViewModel() {

    private var _orderRouteUiState = MutableStateFlow(OrderRouteUiState())
    val orderRouteUiState: StateFlow<OrderRouteUiState> = _orderRouteUiState

    private var _orderRouteUiEvent = MutableSharedFlow<OrderRouteUiEvent>(extraBufferCapacity = 1)
    val orderRouteUiEvent: SharedFlow<OrderRouteUiEvent> = _orderRouteUiEvent

    fun loadRoute() = viewModelScope.launch {
        val result = getCoordinatesUseCase()
        if (!result.isSuccessful()) {
            _orderRouteUiState.update { state -> state.copy(isLoading = false) }
            _orderRouteUiEvent.emit(OrderRouteUiEvent.Error(result.details))
            return@launch
        }

        val pendingLocations = result.data.orEmpty()
        if (pendingLocations.isEmpty()) {
            _orderRouteUiState.update { state -> state.copy(isLoading = false) }
            return@launch
        }

        gpsLocation.getLocationGPS { currentLocation ->
            _orderRouteUiState.update {
                it.copy(
                    isLoading = false,
                    currentLocation = currentLocation,
                    pendingLocations = pendingLocations,
                    nextLocation = getNearestLocation(currentLocation, pendingLocations)
                )
            }
        }
    }

    fun deliverOrder() = viewModelScope.launch {
        val state = _orderRouteUiState.value
        val pendingLocations = markNextLocationAsCompleted(
            nextLocation = state.nextLocation,
            pendingLocations = state.pendingLocations,
        )
        _orderRouteUiState.update {
            it.copy(
                pendingLocations = pendingLocations,
                currentLocation = state.nextLocation,
                nextLocation = getNearestLocation(
                    currentLocation = state.nextLocation,
                    pendingLocations,
                )
            )
        }
    }

    private fun markNextLocationAsCompleted(
        nextLocation: CoordinatesModel?,
        pendingLocations: List<CoordinatesModel>
    ): List<CoordinatesModel> =
        pendingLocations.map { location ->
            if (location == nextLocation) location.copy(status = OrderStatus.COMPLETED) else location
        }

    private fun getNearestLocation(
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
}
