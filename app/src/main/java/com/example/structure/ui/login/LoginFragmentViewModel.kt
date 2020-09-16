package com.example.structure.ui.login

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import com.example.structure.base.BaseViewModel
import com.example.structure.data.DataManager
import com.example.structure.utils.Result

class LoginFragmentViewModel @ViewModelInject constructor(
    private val dataManager: DataManager
) : BaseViewModel() {

    fun callApi() {
        executeSuspendedFunction { dataManager.apiHelper.getProfile() }
    }

    override fun onSucess(result: Any) {
        Log.e("gjgjhgj","ok")
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

}