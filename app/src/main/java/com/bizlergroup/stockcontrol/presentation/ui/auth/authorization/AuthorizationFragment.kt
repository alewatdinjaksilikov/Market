package com.bizlergroup.stockcontrol.presentation.ui.auth.authorization

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bizlergroup.stockcontrol.R
import com.bizlergroup.stockcontrol.data.models.LoginRequestData
import com.bizlergroup.stockcontrol.databinding.FragmentAuthorizationBinding
import com.bizlergroup.stockcontrol.presentation.ui.auth.vm.AuthViewModel
import com.bizlergroup.stockcontrol.utils.SharedPref
import com.bizlergroup.stockcontrol.utils.makeToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthorizationFragment : Fragment(R.layout.fragment_authorization) {

    private lateinit var binding: FragmentAuthorizationBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthorizationBinding.bind(view)

        initObservables()
        initListeners()
    }

    private fun initListeners() {
        binding.btnEnter.setOnClickListener {
            val phoneNumber = binding.etPhoneNumber.text.toString()
            val password = binding.etPassword.text.toString()
            val prefix = binding.phoneNumber.prefixText

            if (phoneNumber != "" && password != "") {
                lifecycleScope.launch {
                    viewModel.authorization(
                        LoginRequestData(
                            password = password,
                            phoneNumber = "$prefix$phoneNumber"
                        )
                    )
                    binding.progressBar.visibility = View.VISIBLE
                }
            } else {
                makeToast("Заполните все поля!!!")
            }
        }

        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(AuthorizationFragmentDirections.actionAuthorizationFragment2ToRegistrationFragment())
        }
    }


    private fun initObservables() {
        viewModel.authorizationFlow.onEach {
            if (it!=null){
                makeToast("Success")
                binding.progressBar.visibility = View.GONE

                SharedPref.prefEditor.putString("token", it.token)
                SharedPref.prefEditor.putString("name",it.name)
                SharedPref.prefEditor.putString("surname",it.surname)
                SharedPref.prefEditor.putString("phoneNumber",it.phoneNumber)
                SharedPref.prefEditor.putBoolean("isLogin",true)
                SharedPref.prefEditor.apply()

                findNavController().navigate(AuthorizationFragmentDirections.actionAuthorizationFragment2ToMainFragment())
            }
        }.launchIn(lifecycleScope)

        viewModel.messageAuthorizationFlow.onEach {
            makeToast(it)
            binding.progressBar.visibility = View.GONE
        }.launchIn(lifecycleScope)

        viewModel.errorAuthorizationFlow.onEach {
            makeToast(it.toString())
            binding.progressBar.visibility = View.GONE
        }.launchIn(lifecycleScope)
    }
}