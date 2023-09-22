package com.bizlergroup.stockcontrol.presentation.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bizlergroup.stockcontrol.R
import com.bizlergroup.stockcontrol.databinding.FragmentSplashBinding
import com.bizlergroup.stockcontrol.utils.SharedPref
import kotlinx.coroutines.delay

class SplashScreen:Fragment(R.layout.fragment_splash) {

    private lateinit var binding : FragmentSplashBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)



        lifecycleScope.launchWhenCreated {
            delay(2300)
            val token = SharedPref.pref.getString("token","")
            val isLogin = SharedPref.pref.getBoolean("isLogin",false)

            if (token!="." && isLogin){
                findNavController().navigate(SplashScreenDirections.actionSplashScreenToMainFragment())
            }else{
                findNavController().navigate(SplashScreenDirections.actionSplashScreenToLoginFragment())
            }
        }

    }
}