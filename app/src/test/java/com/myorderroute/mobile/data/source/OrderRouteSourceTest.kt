/*
 * OrderRouteSourceTest.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.data.source

import com.myorderroute.mobile.data.network.service.OrderRouteService
import com.myorderroute.mobile.utils.CoordinatesMock.coordinatesDataResponse
import com.myorderroute.mobile.utils.CoordinatesMock.coordinatesModelList
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
class OrderRouteSourceTest {

    private lateinit var orderRouteSourceImpl: OrderRouteSourceImpl

    @Mock
    lateinit var orderRouteService: OrderRouteService

    @get:Rule
    val dispatcherRule = DispatcherRule()

    @Before
    fun setUp() {
        orderRouteSourceImpl = OrderRouteSourceImpl(
            orderRouteService,
        )
    }

    @Test
    fun `Get Coordinates From Source Test`(): Unit = runTest {
        val expected = coordinatesModelList
        // Given
        `when`(orderRouteService.getCoordinates()).thenReturn(coordinatesDataResponse)
        // When
        val actual = orderRouteSourceImpl.getCoordinates()
        // Then
        assertEquals(expected, actual.data)
    }
}
