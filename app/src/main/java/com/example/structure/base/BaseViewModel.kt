package com.example.structure.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.structure.utils.Result
import kotlinx.coroutines.launch

abstract class BaseViewModel  : ViewModel() {

    private val _result= MutableLiveData<Result<Any>>()
    val result: LiveData<Result<Any>>
        get() = _result

    fun executeSuspendedFunction(codeBlock: suspend () -> Result<Any>) {
        viewModelScope.launch {
            _result.value = Result.InProgress
            _result.value = codeBlock()
        }
    }


}