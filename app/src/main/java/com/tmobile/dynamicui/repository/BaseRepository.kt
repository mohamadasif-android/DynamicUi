package com.tmobile.dynamicui.repository

import com.tmobile.dynamicui.util.Result
import retrofit2.Response

/**
 * Base Repository class with error handling
 */
abstract class BaseRepository {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        val response = call()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return Result(Result.Status.SUCCESS, response.body(), null)
            }
        }
        return error("${response.code()} ${response.message()}")
    }


    private fun <T> error(message: String): Result<T> {
        //TODO: Instead of string message, can send string resource(Int) value to support localization
        return Result(Result.Status.ERROR, null, message)
    }

}