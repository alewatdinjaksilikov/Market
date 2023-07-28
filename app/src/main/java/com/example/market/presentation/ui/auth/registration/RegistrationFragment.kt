package com.example.market.presentation.ui.auth.registration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.market.R
import com.example.market.databinding.FragmentRegistrationBinding

class RegistrationFragment:Fragment(R.layout.fragment_registration) {
    private lateinit var binding : FragmentRegistrationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistrationBinding.bind(view)


    }
}