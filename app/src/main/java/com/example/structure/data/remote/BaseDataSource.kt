package com.example.structure.data.remote

import com.example.structure.utils.Result
import retrofit2.Response
import java.io.IOException

abstract class BaseDataSource {

    protected suspend fun <T : Any> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.Success(body)
                } else {
                    Result.UnknownError(null)
                }
            } else {
                Result.ApiError(response.errorBody()!!, response.code())
            }

        } catch (e: Exception) {
            return if (e is IOException) {
                Result.NetworkError(e)
            } else {
                Result.UnknownError(e)
            }
        }
    }
}