package com.example.market.presentation.ui.auth.registration

import android.os.Bundle
import android.util.Log
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

        initObservables()
        initListeners()
    }

    private fun initListeners() {
        binding.apply {
            btnRegistration.setOnClickListener {
                val name = binding.etName.text.toString()
                val surname = binding.etSurname.text.toString()
                val phone = binding.etPhone.text.toString()
                val password = binding.etPassword.text.toString()
                val prefix = binding.phone.prefixText

                if (surname.isNotEmpty() && name.isNotEmpty() && phone.isNotEmpty() && password.isNotEmpty()){
                    lifecycleScope.launch {
                        viewModel.registration(
                            RegistrationRequestData(
                                name = name,
                                password = password,
                                phoneNumber = "$prefix$phone",
                                surname = surname
                            )
                        )
                        binding.progressBar.visibility = View.VISIBLE
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
            it?.let {

                SharedPref.prefEditor.putString("token",it.token)
                SharedPref.prefEditor.putString("name",it.name)
                SharedPref.prefEditor.putString("surname",it.surname)
                SharedPref.prefEditor.putString("phoneNumber",it.phoneNumber)
                SharedPref.prefEditor.putBoolean("isLogin",true)
                SharedPref.prefEditor.apply()

                //Проверка
                Log.d("JJJ", "Regis token ${SharedPref.pref.getString("token","").toString()}")
                Log.d("JJJ", "Regis login ${SharedPref.pref.getBoolean("isLogin",false).toString()}")
            }
            findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToMainFragment())
        }.launchIn(lifecycleScope)

        viewModel.errorRegistration.onEach {
            makeToast(it.toString())
            binding.progressBar.visibility = View.GONE
        }.launchIn(lifecycleScope)

        viewModel.messageRegistration.onEach {
            makeToast(it)
            binding.progressBar.visibility = View.GONE
        }.launchIn(lifecycleScope)
    }
}