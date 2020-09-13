package com.example.structure.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.structure.data.model.LoginResponse
import com.example.structure.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel : ViewModel() {

    private val _result = MutableLiveData<Result<Any>>()
    val result: LiveData<Result<Any>>
        get() = _result

    val isLoading = MutableLiveData<Boolean>()

    fun executeSuspendedFunction(codeBlock: suspend () -> Result<Any>) {
        viewModelScope.launch {
            isLoading.value = true
            val it = withContext(Dispatchers.IO) {
                codeBlock()
            }
            isLoading.value = false
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
            }

        }
    }

    abstract fun onSucess(result: Any)
    abstract fun onApiError(result: Result.ApiError)
    abstract fun onNetworkError(result: Result.NetworkError)
    abstract fun onUnknownError(result: Result.UnknownError)


}