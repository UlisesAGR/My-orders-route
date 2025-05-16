/*
 * CoordinatesMapper.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.domain.mapper

import com.myorderroute.mobile.data.network.model.CoordinatesResponse
import com.myorderroute.mobile.domain.model.CoordinatesModel

fun CoordinatesResponse.toDomain(): CoordinatesModel =
    CoordinatesModel(name, latitude, longitude)
