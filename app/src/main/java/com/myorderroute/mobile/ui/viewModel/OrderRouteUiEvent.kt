/*
 * OrderRouteUiEvent.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.ui.viewModel

sealed class OrderRouteUiEvent {
    internal data class Error(val message: String?) : OrderRouteUiEvent()
}
