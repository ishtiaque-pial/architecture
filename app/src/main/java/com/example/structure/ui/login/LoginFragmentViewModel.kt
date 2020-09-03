package com.example.structure.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import com.example.structure.base.BaseViewModel
import com.example.structure.data.DataManager

class LoginFragmentViewModel @ViewModelInject constructor(
    private val dataManager: DataManager
) : BaseViewModel() {

    fun callApi() {
        executeSuspendedFunction { dataManager.apiHelper.getProfile() }
    }

}