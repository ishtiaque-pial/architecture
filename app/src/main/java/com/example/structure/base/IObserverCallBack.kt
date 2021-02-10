package com.example.structure.base

import com.example.structure.utils.Result


interface IObserverCallBack {
    fun onApiError(result: Result.ApiError)
    fun onNetworkError(result: Result.NetworkError)
    fun onUnknownError(result: Result.UnknownError)
    fun inProgress(result: Result.InProgress)
}