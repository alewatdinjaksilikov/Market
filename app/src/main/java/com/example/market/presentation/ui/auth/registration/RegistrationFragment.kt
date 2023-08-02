package com.example.market.presentation.ui.auth.registration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.market.R
import com.example.market.data.models.RegistrationRequestData
import com.example.market.databinding.FragmentRegistrationBinding
import com.example.market.presentation.ui.auth.vm.AuthViewModel
import com.example.market.utils.SharedPref
import com.example.market.utils.makeToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegistrationFragment:Fragment(R.layout.fragment_registration) {
    private lateinit var binding : FragmentRegistrationBinding
    private val viewModel : AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistrationBinding.bind(view)

        initVariables()
        initObservables()
        initListeners()
    }

    private fun initListeners() {
        binding.apply {
            btnRegistration.setOnClickListener {
                val name = binding.etName.text.toString()
                val phone = binding.etPhone.text.toString()
                val password = binding.etPassword.text.toString()

                if (name.isNotEmpty() && phone.isNotEmpty() && password.isNotEmpty()){
                    lifecycleScope.launch {
                        viewModel.registration(
                            RegistrationRequestData(
                                name = name,
                                password = password,
                                phoneNumber = phone
                            )
                        )
                    }
                }else{
                    makeToast("Заполните все поля!!!")
                }
            }

            tvLogin.setOnClickListener {
                findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToAuthorizationFragment2())
            }
        }
    }

    private fun initObservables() {
        viewModel.registrationFlow.onEach {
            makeToast("Success")
            SharedPref.pref.edit().putString("token",it.token).apply()
            SharedPref.pref.edit().putInt("login",1).apply()
            findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToMainFragment())
        }.launchIn(lifecycleScope)
    }

    private fun initVariables() {

    }
}