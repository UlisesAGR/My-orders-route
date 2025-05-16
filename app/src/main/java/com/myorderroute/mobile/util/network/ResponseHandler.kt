/*
 * Resource.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.util.network

sealed class Resource<out T>(
    val status: Int,
    val data: T? = null,
    val stringCode: String? = null,
    val details: String? = null,
) {
    class Success<T>(
        data: T,
        details: String? = null,
        stringCode: String? = null,
    ) : Resource<T>(
        status = SUCCESS,
        data = data,
        details = details,
        stringCode = stringCode,
    )

    class Failure<T>(
        status: Int,
        stringCode: String?,
        details: String?,
    ) : Resource<T>(
        status = status,
        stringCode = stringCode,
        details = details,
    )

    fun isSuccessful(): Boolean = status in 200..300

    companion object {
        const val SUCCESS = 200
    }
}
