package com.example.structure.data.remote

import com.example.structure.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("profile")
    suspend fun getProfile(): Response<LoginResponse>
}