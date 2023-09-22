package com.bizlergroup.stockcontrol.presentation.ui.setting.editProfile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bizlergroup.stockcontrol.R
import com.bizlergroup.stockcontrol.data.models.EditProfileRequestData
import com.bizlergroup.stockcontrol.databinding.FragmentEditProfileBinding
import com.bizlergroup.stockcontrol.presentation.ui.setting.vm.SettingFragmentViewModel
import com.bizlergroup.stockcontrol.utils.SharedPref
import com.bizlergroup.stockcontrol.utils.makeToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditProfileFragment:Fragment(R.layout.fragment_edit_profile) {
    private lateinit var binding:FragmentEditProfileBinding
    private val viewModel : SettingFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditProfileBinding.bind(view)

        initVariables()
        initObservables()
        initListeners()

    }

    private fun initObservables() {
        viewModel.editProfile.onEach {
            SharedPref.pref.edit().putString("token",it.token).apply()
            SharedPref.pref.edit().putString("name",it.name).apply()
            SharedPref.pref.edit().putString("surname",it.surname).apply()
            SharedPref.pref.edit().putString("phoneNumber",it.phoneNumber).apply()
            makeToast("Сохранено")
            findNavController().popBackStack()
        }.launchIn(lifecycleScope)
    }

    private fun initListeners() {
        binding.apply {
            btnSave.setOnClickListener {
                val surname = binding.etSurname.text.toString()
                val name = binding.etName.text.toString()
                val phoneNumber = binding.etPhoneNumber.text.toString()

                if (surname!="" && name!="" && phoneNumber!=""){
                    lifecycleScope.launch {
                        viewModel.editProfile(
                            EditProfileRequestData(
                                name = name,
                                phoneNumber = phoneNumber,
                                surname = surname
                            )
                        )
                    }
                }else{
                    makeToast("Заполните все поля!!!")
                }
            }
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initVariables() {

        binding.apply {
            tvSurname.text = SharedPref.pref.getString("surname","Surname")
            tvName.text = SharedPref.pref.getString("name","Name")
            etSurname.setText(SharedPref.pref.getString("surname","Surname"))
            etName.setText(SharedPref.pref.getString("name","Name"))
            etPhoneNumber.setText(SharedPref.pref.getString("phoneNumber","Phone Number"))
        }
    }
}