package com.bizlergroup.stockcontrol.presentation.ui.auth.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bizlergroup.stockcontrol.R
import com.bizlergroup.stockcontrol.databinding.FragmentLoginBinding

class LoginFragment:Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        initListeners()

    }

    private fun initListeners() {
        binding.apply {
            btnRegistration.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
            }
            btnAuthorization.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToAuthorizationFragment2())
            }
        }
    }
}