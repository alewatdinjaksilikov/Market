package com.example.market.presentation.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.market.R
import com.example.market.databinding.DialogListProductsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListProductsDialog:DialogFragment() {

    private lateinit var binding:DialogListProductsBinding

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.95).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.43).toInt()
        dialog!!.window?.setLayout(width, height)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_list_products,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogListProductsBinding.bind(view)

    }
}