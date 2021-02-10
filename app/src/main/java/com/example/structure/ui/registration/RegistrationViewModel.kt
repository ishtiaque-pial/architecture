package com.example.structure.ui.registration

import androidx.hilt.lifecycle.ViewModelInject
import com.example.structure.base.BaseViewModel
import com.example.structure.data.DataManager
import com.example.structure.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val dataManager: DataManager
) : BaseViewModel() {
    override fun onSucess(result: Any) {

    }

    override fun onApiError(result: Result.ApiError) {

    }

    override fun onNetworkError(result: Result.NetworkError) {

    }

    override fun onUnknownError(result: Result.UnknownError) {
        
    }

    override fun inProgress(result: Result.InProgress) {

    }
}