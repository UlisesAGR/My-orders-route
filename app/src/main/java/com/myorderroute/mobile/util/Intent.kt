/*
 * Intent.kt
 * Created by Eduardo
 * Copyright (c) 2025. All rights reserved
 */

package com.myorderroute.mobile.util

import android.content.Intent
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.nextActivity(destination: FragmentActivity) {
    Intent(this, destination::class.java).apply {
        startActivity(this)
        finish()
    }
}
