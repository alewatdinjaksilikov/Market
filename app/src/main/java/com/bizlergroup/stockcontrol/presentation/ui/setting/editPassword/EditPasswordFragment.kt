package com.bizlergroup.stockcontrol.presentation.ui.setting.editPassword

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bizlergroup.stockcontrol.R
import com.bizlergroup.stockcontrol.data.models.EditPasswordRequestData
import com.bizlergroup.stockcontrol.databinding.FragmentEditPasswordBinding
import com.bizlergroup.stockcontrol.presentation.ui.setting.vm.SettingFragmentViewModel
import com.bizlergroup.stockcontrol.utils.makeToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditPasswordFragment:Fragment(R.layout.fragment_edit_password) {
    private lateinit var binding:FragmentEditPasswordBinding
    private val viewModel : SettingFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditPasswordBinding.bind(view)

        initListeners()
        initObservables()

    }

    private fun initObservables() {
        viewModel.editPassword.onEach {
            if (it.statusCode == 200){
                makeToast(it.message)
                findNavController().popBackStack()
            }else{
                makeToast(it.message)
            }
        }.launchIn(lifecycleScope)
    }

    private fun initListeners() {
        binding.apply {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnUpdatePassword.setOnClickListener {
                val lastPassword = binding.etLastPassword.text.toString()
                val newPassword = binding.etNewPassword.text.toString()
                val confirmNewPassword = binding.etConfirmNewPassword.text.toString()

                if (lastPassword!="" && newPassword!="" && confirmNewPassword!="" && newPassword==confirmNewPassword){
                    lifecycleScope.launch {
                        viewModel.editPassword(
                            body = EditPasswordRequestData(
                                currentPass = lastPassword,
                                newPass = newPassword
                            )
                        )
                    }
                }else if(newPassword!==confirmNewPassword && lastPassword!=""){
                    makeToast("Новый пароль не совпадает с подтвержденным паролем")
                }else{
                    makeToast("Заполните все поля!!!")
                }
            }
        }
    }
}