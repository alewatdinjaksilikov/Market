package com.example.market.presentation.ui.StockScreen

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.market.R
import com.example.market.databinding.FragmentStockBinding
import com.example.market.presentation.ui.StockScreen.adapter.StockFragmentCategoryAdapter
import com.example.market.presentation.ui.StockScreen.adapter.StockFragmentProductAdapter
import com.example.market.presentation.vm.StockFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StockFragment : Fragment(R.layout.fragment_stock) {
    private lateinit var binding: FragmentStockBinding
    private var adapterCategory = StockFragmentCategoryAdapter()
    private var adapterProducts = StockFragmentProductAdapter()
    private val viewModel : StockFragmentViewModel by viewModels()
    private var clickedCategory = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.getAllCategories()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStockBinding.bind(view)

        initVariables()
        initObservables()
        initListeners()
    }

    private fun initObservables() {
        viewModel.getAllCategoriesFlow.onEach {
            adapterCategory.submitList(it)
            binding.shimmerStockCategory.stopShimmer()
            binding.shimmerStockCategory.visibility = View.GONE
            viewModel.getAllProductByCategory(clickedCategory)
        }.launchIn(lifecycleScope)

        viewModel.getAllProduct.onEach {
            adapterProducts.submitList(it)
            binding.shimmerStockProducts.stopShimmer()
            binding.shimmerStockProducts.visibility = View.GONE
        }.launchIn(lifecycleScope)

        viewModel.deleteProductFlow.onEach {
            Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
            viewModel.getAllProductByCategory(clickedCategory)
        }.launchIn(lifecycleScope)
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        adapterCategory.setOnItemClick {
            clickedCategory = it.id
            lifecycleScope.launch{
                viewModel.getAllProductByCategory(clickedCategory)
            }
        }

        adapterProducts.setOnClickPopUpMenu { binding,product->
            val popupMenu = PopupMenu(requireContext(), binding.btnEdit)
            popupMenu.menuInflater.inflate(R.menu.pop_up_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.edit -> findNavController().navigate(StockFragmentDirections.actionFragmentStockToEditProductDialog(product.name,product.id))
                    R.id.delete -> {
                        val dialog = AlertDialog.Builder(requireContext())
                            .setTitle("Удаление")
                            .setMessage("Вы действительно хотите удалить продукт ${product.name} ?")
                            .setPositiveButton("Да") { p0, p1 ->
                                lifecycleScope.launch {
                                    viewModel.deleteProduct(product.id)
                                    viewModel.getAllProductByCategory(clickedCategory)
                                }
                            }
                            .setNegativeButton("Нет"){ p0, p1 ->
                                p0.dismiss()
                            }
                        dialog.show()
                    }
                }
                true
            }
            popupMenu.show()
        }

        binding.btnMenuCategory.setOnClickListener {
            val popupMenu = PopupMenu(requireContext(), binding.btnMenuCategory)
            popupMenu.menuInflater.inflate(R.menu.pop_up_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.edit -> findNavController().navigate(StockFragmentDirections.actionFragmentStockToEditCategoryDialog())
                    R.id.delete -> Toast.makeText(
                        requireContext(),
                        "You Clicked : " + item.title,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                true
            }
            popupMenu.show()
        }
    }

    private fun initVariables() {
        binding.rvStockCategory.adapter = adapterCategory
        binding.rvStockProducts.adapter = adapterProducts
    }
}