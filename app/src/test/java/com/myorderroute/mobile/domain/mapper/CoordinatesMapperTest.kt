/*
 * CoordinatesMapperTest.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.domain.mapper

import com.myorderroute.mobile.utils.CoordinatesMock.coordinatesModel
import com.myorderroute.mobile.utils.CoordinatesMock.coordinatesResponse
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CoordinatesMapperTest {

    @Test
    fun `Validate Transformation Coordinates Response To Domain`() = runTest {
        // Given
        val expected = coordinatesModel
        // When
        val actual = coordinatesResponse.toDomain()
        // Then
        assertEquals(expected, actual)
    }
}
