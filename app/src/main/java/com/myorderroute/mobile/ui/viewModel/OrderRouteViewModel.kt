/*
 * OrderRouteViewModel.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myorderroute.mobile.domain.usecase.GetCoordinatesUseCase
import com.myorderroute.mobile.util.location.GpsLocation
import com.myorderroute.mobile.util.location.getNearestLocation
import com.myorderroute.mobile.util.location.markNextLocationAsCompleted
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
}
