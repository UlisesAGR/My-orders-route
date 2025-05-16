/*
 * LocationSearchPriority.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.util.location

import com.google.android.gms.location.Priority

enum class LocationSearchPriority(val timeInMilli: Long, val type: Int) {
    HIGH(10_000L, Priority.PRIORITY_HIGH_ACCURACY),
    LOW(5_000L, Priority.PRIORITY_BALANCED_POWER_ACCURACY)
}
