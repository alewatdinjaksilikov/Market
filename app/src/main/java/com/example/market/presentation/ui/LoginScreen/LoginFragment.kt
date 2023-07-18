package com.example.market.presentation.ui.LoginScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.market.R
import com.example.market.databinding.FragmentLoginBinding

class LoginFragment:Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

    }
}