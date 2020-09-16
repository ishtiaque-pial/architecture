package com.example.structure.data.remote

import android.util.Log

class ApiHelper(private val apiService: ApiService):BaseDataSource() {

    suspend fun getProfile() = getResultFlow { apiService.getProfile() }
}