package com.example.market.presentation.ui.StockScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.market.R
import com.example.market.databinding.FragmentStockBinding

class StockFragment:Fragment(R.layout.fragment_stock) {
    private lateinit var binding: FragmentStockBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStockBinding.bind(view)

    }
}