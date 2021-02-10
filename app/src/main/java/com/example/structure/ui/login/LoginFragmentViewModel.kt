package com.example.structure.ui.login

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import com.example.structure.base.BaseViewModel
import com.example.structure.data.DataManager
import com.example.structure.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(
    private val dataManager: DataManager
) : BaseViewModel() {

    fun callApi() {
        executeSuspendedFunction { dataManager.apiHelper.getProfile() }
    }

    override fun onSucess(result: Any) {
        Log.e("gjgjhgj","ok")
    }

}