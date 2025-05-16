/*
 * CoordinatesModel.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.domain.model

import com.myorderroute.mobile.domain.state.OrderStatus

data class CoordinatesModel(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val status: OrderStatus = OrderStatus.PENDING,
)
