package com.example.market.presentation.ui.home.dialog.product
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.market.R
import com.example.market.databinding.DialogProductsBinding
import com.example.market.presentation.ui.home.dialog.product.adapter.AdapterProductDialog
import com.example.market.presentation.ui.home.screen.vm.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsDialog:DialogFragment() {

    private lateinit var binding: DialogProductsBinding
    private val viewModel : HomeFragmentViewModel by viewModels()
    private val adapter = AdapterProductDialog()

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
        return inflater.inflate(R.layout.dialog_products,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogProductsBinding.bind(view)

        initVariables()
        initObservables()
        initListeners()
    }

    private fun initVariables() {
        binding.rvProduct.adapter = adapter
        lifecycleScope.launch {
            viewModel.getAllProducts()
        }
    }

    private fun initObservables() {
        viewModel.getAllProductsFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)
    }

    private fun initListeners() {
        adapter.setOnItemClick {
            findNavController().previousBackStackEntry?.savedStateHandle?.set("nameProduct",it.name)
            findNavController().popBackStack()
        }
    }
}