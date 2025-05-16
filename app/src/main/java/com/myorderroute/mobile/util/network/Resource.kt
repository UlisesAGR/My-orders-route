/*
 * ResponseHandler.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.util.network

import retrofit2.Response

fun <R, T> Response<R>.toResult(action: R.() -> T): Resource<T> =
    toResource(
        response = this,
        action,
    )

private fun <R, T> toResource(
    response: Response<R>,
    action: R.() -> T
): Resource<T> {
    val statusCode = response.code()
    val message = response.message()
    return if (response.isSuccessful) {
        response.body()?.let { body ->
            Resource.Success(body.action())
        } ?: Resource.Failure(
            status = statusCode,
            stringCode = statusCode.toString(),
            details = message,
        )
    } else {
        Resource.Failure(
            status = statusCode,
            stringCode = statusCode.toString(),
            details = message,
        )
    }
}

fun <T> Response<T>.toResult(): Resource<T> {
    val statusCode = this.code()
    val message = this.message()
    return if (this.isSuccessful) {
        this.body()?.let {
            Resource.Success(it)
        } ?: Resource.Failure(
            status = statusCode,
            stringCode = statusCode.toString(),
            details = message,
        )
    } else {
        Resource.Failure(
            status = statusCode,
            stringCode = statusCode.toString(),
            details = message,
        )
    }
}
