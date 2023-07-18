package com.example.market.presentation.ui.dialogs.listProducts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.market.R
import com.example.market.databinding.DialogListProductsBinding
import com.example.market.presentation.vm.ListProductsDialogViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListProductsDialog:DialogFragment() {

    private lateinit var binding:DialogListProductsBinding
    private val args : ListProductsDialogArgs by navArgs()
    private val adapter = ListProductsAdapter()
    private val viewModel : ListProductsDialogViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.95).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.65).toInt()
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

        initVariables()
        initObservables()
        initListeners()
    }

    private fun initListeners() {

    }

    private fun initObservables() {
        viewModel.getAllProductsFlow.onEach {
            if (it.isEmpty()){
                binding.tvNoProducts.visibility = View.VISIBLE
            }else{
             adapter.submitList(it)
            }
        }.launchIn(lifecycleScope)
    }

    private fun initVariables() {
        binding.rvListProducts.adapter = adapter
        binding.tvNameCategory.text = args.name
        lifecycleScope.launch {
            viewModel.getAllProductByCategories(args.id)
        }
    }
}