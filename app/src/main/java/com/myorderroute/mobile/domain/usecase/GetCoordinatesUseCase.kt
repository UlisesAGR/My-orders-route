/*
 * GetCoordinatesUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.domain.usecase

import com.myorderroute.mobile.domain.model.CoordinatesModel
import com.myorderroute.mobile.domain.repository.OrderRouteRepository
import com.myorderroute.mobile.util.network.Resource
import javax.inject.Inject

class GetCoordinatesUseCase @Inject constructor(
    private val orderRouteRepository: OrderRouteRepository,
) {

    suspend operator fun invoke(): Resource<List<CoordinatesModel>> =
        orderRouteRepository.getCoordinates()
}
