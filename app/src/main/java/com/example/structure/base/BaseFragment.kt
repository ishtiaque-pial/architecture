package com.example.structure.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.structure.utils.Result

abstract class BaseFragment:Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel().result.observe(viewLifecycleOwner, Observer {
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
                is Result.InProgress->{
                    onProgress(it)
                }
            }
        })

    }

    protected abstract fun getViewModel() : BaseViewModel
    abstract fun onSucess(result: Any)
    abstract fun onApiError(result: Result.ApiError)
    abstract fun onNetworkError(result: Result.NetworkError)
    abstract fun onUnknownError(result: Result.UnknownError)
    abstract fun onProgress(result: Result.InProgress)
}