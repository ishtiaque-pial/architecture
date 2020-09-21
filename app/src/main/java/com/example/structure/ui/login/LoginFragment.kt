package com.example.structure.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.structure.R
import com.example.structure.base.BaseFragment
import com.example.structure.base.BaseViewModel
import com.example.structure.data.model.LoginResponse
import com.example.structure.databinding.FragmentLoginBinding
import com.example.structure.utils.Result
import com.example.structure.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    private lateinit var bindingView: FragmentLoginBinding
    private val viewModel: LoginFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingView = DataBindingUtil.inflate(inflater,
            R.layout.fragment_login, container, false)
        bindingView.viewModel = viewModel
        viewModel.callApi()
        bindingView.btn.setOnClickListener {
           findNavController().navigate(R.id.action_loginFragment_to_registrationFragment2)
        }
        return bindingView.root
    }

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onSucess(result: Any) {
        if (result is LoginResponse) {
            bindingView.btn.text = result.name
        }
    }

    override fun onApiError(result: Result.ApiError) {
        bindingView.btn.text = "ApiError"
    }

    override fun onNetworkError(result: Result.NetworkError) {
        bindingView.btn.text = "NetworkError"
    }

    override fun onUnknownError(result: Result.UnknownError) {
        bindingView.btn.text = "UnknownError"
    }

    override fun onProgress(result: Result.InProgress) {
        activity?.showToast("is progress running "+result.isLoading)
    }
}