package com.example.structure.base

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

abstract class BaseViewModel : ViewModel() {

    private val _result = MutableLiveData<Result<Any>>()
    val result: LiveData<Result<Any>>
        get() = _result


    fun executeSuspendedFunction(codeBlock: suspend () -> Flow<Result<Any>>) {
        viewModelScope.launch {
            codeBlock().collect {
                _result.value = it
            }
        }
    }

}