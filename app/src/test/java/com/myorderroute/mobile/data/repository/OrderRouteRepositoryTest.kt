/*
 * OrderRouteRepositoryTest.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.data.repository

import com.myorderroute.mobile.data.source.OrderRouteSource
import com.myorderroute.mobile.utils.CoordinatesMock.coordinatesModelList
import com.myorderroute.mobile.utils.CoordinatesMock.coordinatesModelListResource
import com.myorderroute.mobile.utils.DispatcherRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.StandardTestDispatcher
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
class OrderRouteRepositoryTest {

    private lateinit var orderRouteRepositoryImpl: OrderRouteRepositoryImpl

    @Mock
    lateinit var orderRouteSource: OrderRouteSource

    @get:Rule
    val dispatcherRule = DispatcherRule()

    @Before
    fun setUp() {
        orderRouteRepositoryImpl = OrderRouteRepositoryImpl(
            orderRouteSource,
            StandardTestDispatcher(),
        )
    }

    @Test
    fun `Verify Current User Test`(): Unit = runTest {
        val expected = coordinatesModelList
        // Given
        `when`(orderRouteSource.getCoordinates()).thenReturn(coordinatesModelListResource)
        // When
        val actual = orderRouteRepositoryImpl.getCoordinates()
        // Then
        assertEquals(expected, actual.data)
    }
}
