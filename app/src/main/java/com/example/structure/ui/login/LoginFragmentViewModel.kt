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



}