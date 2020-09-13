package com.example.structure.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.structure.R
import com.example.structure.data.model.LoginResponse
import com.example.structure.databinding.FragmentLoginBinding
import com.example.structure.utils.Result
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {

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

        viewModel.result.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Result.Success -> {
                    if (it.data is LoginResponse) {
                        bindingView.btn.text = it.data.name
                    }
                }
                is Result.ApiError -> {
                    bindingView.btn.text = "ApiError"
                }
                is Result.NetworkError -> {
                    bindingView.btn.text = "NetworkError"
                }
                is Result.UnknownError -> {
                    bindingView.btn.text = "UnknownError"
                }
                is Result.InProgress -> {
                    if (it.isLoading) {
                        bindingView.btn.text = "InProgress"
                    } else {
                       // bindingView.btn.text = "Stop"
                    }
                }
            }
        })

        return bindingView.root
    }


}