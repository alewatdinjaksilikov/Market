package com.example.market.presentation.ui.AddScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.market.R
import com.example.market.databinding.FragmentAddBinding

class AddScreen:Fragment(R.layout.fragment_add) {
    private lateinit var binding: FragmentAddBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddBinding.bind(view)


    }
}