package com.example.structure.data.remote

import com.example.structure.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.io.IOException

abstract class BaseDataSource {

    protected suspend fun <T : Any> getResultFlow(call: suspend () -> Response<T>): Flow<Result<T>> {
        return flow {
            try {
                emit(Result.InProgress(true))
                val response = call()
                emit(Result.InProgress(false))
                emit( if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        Result.Success(body)
                    } else {
                        Result.UnknownError(null)
                    }
                } else {
                    Result.ApiError(response.errorBody()!!, response.code())
                })

            } catch (e: Exception) {
                emit(Result.InProgress(false))
                emit( if (e is IOException) {
                    Result.NetworkError(e)
                } else {
                    Result.UnknownError(e)
                })
            }
        }.flowOn(Dispatchers.IO)

    }
}