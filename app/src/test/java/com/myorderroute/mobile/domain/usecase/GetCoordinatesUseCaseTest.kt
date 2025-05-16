/*
 * GetCoordinatesUseCaseTest.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.domain.usecase

import com.myorderroute.mobile.domain.repository.OrderRouteRepository
import com.myorderroute.mobile.utils.CoordinatesMock.coordinatesModelList
import com.myorderroute.mobile.utils.CoordinatesMock.coordinatesModelListResource
import com.myorderroute.mobile.utils.DispatcherRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@HiltAndroidTest
class GetCoordinatesUseCaseTest {

    private lateinit var getCoordinatesUseCase: GetCoordinatesUseCase

    @Mock
    lateinit var orderRouteRepository: OrderRouteRepository

    @get:Rule
    val dispatcherRule = DispatcherRule()

    @Before
    fun setUp() {
        getCoordinatesUseCase = GetCoordinatesUseCase(
            orderRouteRepository,
        )
    }

    @Test
    fun `Get Coordinates From Source Test`(): Unit = runTest {
        val expected = coordinatesModelList
        // Given
        `when`(orderRouteRepository.getCoordinates()).thenReturn(coordinatesModelListResource)
        // When
        val actual = getCoordinatesUseCase()
        // Then
        assertEquals(expected, actual.data)
    }
}
