package com.example.structure.ui.registration

import androidx.hilt.lifecycle.ViewModelInject
import com.example.structure.base.BaseViewModel
import com.example.structure.data.DataManager

class RegistrationViewModel @ViewModelInject constructor(
    private val dataManager: DataManager
) : BaseViewModel() {
}