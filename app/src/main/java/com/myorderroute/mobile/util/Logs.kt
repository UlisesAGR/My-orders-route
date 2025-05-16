/*
 * Logs.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.util

import android.util.Log
import com.myorderroute.mobile.util.Constants.ERROR
import com.myorderroute.mobile.util.Constants.NO_MESSAGE

fun log(message: String) {
    Log.d(ERROR, message)
}

fun log(
    className: Any,
    message: String?,
) {
    Log.d(className.javaClass.simpleName, message ?: NO_MESSAGE)
}
