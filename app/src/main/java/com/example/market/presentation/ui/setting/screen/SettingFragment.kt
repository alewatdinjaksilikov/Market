package com.example.market.presentation.ui.setting.screen

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.market.R
import com.example.market.databinding.FragmentSettingBinding
import com.example.market.presentation.ui.main.MainFragment
import com.example.market.utils.SharedPref
import dagger.hilt.android.AndroidEntryPoint
import kotlin.system.exitProcess

@AndroidEntryPoint
class SettingFragment:Fragment(R.layout.fragment_setting) {
    private lateinit var binding: FragmentSettingBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingBinding.bind(view)

        initVariables()
        initListeners()

    }

    private fun initListeners() {
        binding.apply {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
                MainFragment.BottomNavigationViewVisibilityLiveData.setVisibility(View.VISIBLE)
                MainFragment.FloatActionButtonVisibilityLiveData.setVisibility(View.VISIBLE)
            }
            btnEdit.setOnClickListener {
                findNavController().navigate(SettingFragmentDirections.actionSettingFragmentToEditProfileFragment())
            }
            btnLogout.setOnClickListener {

                requireContext().getSharedPreferences("MySharedPref",0).edit().clear().apply()

                SharedPref.pref.edit().putString("token", ".").apply()
                SharedPref.pref.edit().putBoolean("isLogin", false).apply()

                Log.d("JJJ", "Setting token ${SharedPref.pref.getString("token","").toString()}")
                Log.d("JJJ", "Setting log out ${SharedPref.pref.getBoolean("isLogin",false).toString()}")
                exitProcess(0)
            }
            llEditPassword.setOnClickListener {
                findNavController().navigate(SettingFragmentDirections.actionSettingFragmentToEditPasswordFragment())
            }
        }

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (isEnabled) {
                        isEnabled = false
                        requireActivity().onBackPressed()
                        MainFragment.BottomNavigationViewVisibilityLiveData.setVisibility(View.VISIBLE)
                        MainFragment.FloatActionButtonVisibilityLiveData.setVisibility(View.VISIBLE)
                    }
                }
            }
            )
    }

    private fun initVariables() {
        binding.tvName.text = SharedPref.pref.getString("name","Name")
        binding.tvSurname.text = SharedPref.pref.getString("surname","Surname")
    }

}