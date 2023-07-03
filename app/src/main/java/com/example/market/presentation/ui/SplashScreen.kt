package com.example.market.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.market.R
import com.example.market.databinding.FragmentSplashBinding

class SplashScreen:Fragment(R.layout.fragment_splash) {

    private lateinit var binding : FragmentSplashBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)

    }
}