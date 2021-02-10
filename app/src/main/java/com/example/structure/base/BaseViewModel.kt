package com.example.structure.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.structure.data.model.LoginResponse
import com.example.structure.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel : ViewModel(),IObserverCallBack {

    private val _result = MutableLiveData<Result<Any>>()

    fun executeSuspendedFunction(codeBlock: suspend () -> Flow<Result<Any>>) {
        viewModelScope.launch {
            codeBlock().collect {
                _result.value = it
                when (it) {
                    is Result.Success -> {
                        onSucess(it.data)
                    }
                    is Result.ApiError -> {
                        onApiError(it)
                    }
                    is Result.NetworkError -> {
                        onNetworkError(it)
                    }
                    is Result.UnknownError -> {
                        onUnknownError(it)
                    }
                    is Result.InProgress -> {
                        inProgress(it)
                    }
                }
            }
        }
    }

    override fun onApiError(result: Result.ApiError) {
        Log.e("gjgjhgj","ApiError")
    }

    override fun onNetworkError(result: Result.NetworkError) {
        Log.e("gjgjhgj","NetworkError")
    }

    override fun onUnknownError(result: Result.UnknownError) {
        Log.e("gjgjhgj","UnknownError")
    }

    override fun inProgress(result: Result.InProgress) {
        if (result.isLoading) {
            Log.e("gjgjhgj","Start")
        } else {
            Log.e("gjgjhgj","Finish")
        }
    }


    protected abstract fun onSucess(result: Any)

}