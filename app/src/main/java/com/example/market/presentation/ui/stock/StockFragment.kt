package com.example.market.presentation.ui.stock

import android.app.AlertDialog
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.text.bold
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.market.R
import com.example.market.databinding.FragmentStockBinding
import com.example.market.presentation.ui.stock.adapter.StockFragmentCategoryAdapter
import com.example.market.presentation.ui.stock.adapter.StockFragmentProductAdapter
import com.example.market.presentation.ui.stock.vm.StockFragmentViewModel
import com.example.market.utils.AddAmountClick
import com.example.market.utils.AddButtonCategoryClick
import com.example.market.utils.AddButtonClick
import com.example.market.utils.EditCategoryClick
import com.example.market.utils.EditProductClick
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StockFragment : Fragment(R.layout.fragment_stock) {


    private lateinit var binding: FragmentStockBinding
    private var adapterCategory = StockFragmentCategoryAdapter()
    private var adapterProducts = StockFragmentProductAdapter()
    private val viewModel : StockFragmentViewModel by viewModels()
    private var clickedCategory = 0



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

        viewModel.deleteCategoryFlow.onEach {
            binding.swipeRefreshStock.isRefreshing = true
            if (it.statusCode == 202){
                lifecycleScope.launch {
                    viewModel.getAllCategories()
                    viewModel.getAllProductByCategory(clickedCategory)
                }
                binding.swipeRefreshStock.isRefreshing = false
            }
        }.launchIn(lifecycleScope)


        viewModel.getAllCategoriesFlow.onEach {
            if (it.isEmpty()){
                binding.tvNoCategory.visibility = View.VISIBLE
            }else{
                binding.tvNoCategory.visibility = View.GONE
            }
            adapterCategory.submitList(it)
            binding.shimmerStockCategory.stopShimmer()
            binding.shimmerStockCategory.visibility = View.GONE
            viewModel.getAllProductByCategory(clickedCategory)
        }.launchIn(lifecycleScope)

        viewModel.getAllProduct.onEach {
            if (it.isEmpty()){
                binding.tvNoProducts.visibility = View.VISIBLE
            }else{
                binding.tvNoProducts.visibility = View.GONE
            }
            adapterProducts.submitList(it)
            binding.shimmerStockProducts.stopShimmer()
            binding.shimmerStockProducts.visibility = View.GONE
        }.launchIn(lifecycleScope)

        viewModel.deleteProductFlow.onEach {
            Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
            binding.swipeRefreshStock.isRefreshing = true
            if (it.statusCode == 202){
                lifecycleScope.launch {
                    viewModel.getAllCategories()
                    viewModel.getAllProductByCategory(clickedCategory)
                }
                binding.swipeRefreshStock.isRefreshing = false
            }
        }.launchIn(lifecycleScope)

        lifecycleScope.launch {
            AddButtonClick.buttonClickLiveData.observe(viewLifecycleOwner){
                if (it){
                    binding.swipeRefreshStock.isRefreshing = true
                        lifecycleScope.launch {
                            viewModel.getAllCategories()
                            viewModel.getAllProductByCategory(clickedCategory)
                        }
                        binding.swipeRefreshStock.isRefreshing = false
                }
            }

            AddButtonCategoryClick.buttonAddCategoryLiveData.observe(viewLifecycleOwner){
                if (it){
                    binding.swipeRefreshStock.isRefreshing = true
                    lifecycleScope.launch {
                        viewModel.getAllCategories()
                        viewModel.getAllProductByCategory(clickedCategory)
                    }
                    binding.swipeRefreshStock.isRefreshing = false
                }
            }

            EditProductClick.buttonEditProductLiveData.observe(viewLifecycleOwner){
                if (it){
                    binding.swipeRefreshStock.isRefreshing = true
                    lifecycleScope.launch {
                        viewModel.getAllCategories()
                        viewModel.getAllProductByCategory(clickedCategory)
                    }
                    binding.swipeRefreshStock.isRefreshing = false
                }
            }

            EditCategoryClick.buttonEditCategoryLiveData.observe(viewLifecycleOwner){
                if (it){
                    binding.swipeRefreshStock.isRefreshing = true
                    lifecycleScope.launch {
                        viewModel.getAllCategories()
                        viewModel.getAllProductByCategory(clickedCategory)
                    }
                    binding.swipeRefreshStock.isRefreshing = false
                }
            }

            AddAmountClick.buttonAddAmountLiveData.observe(viewLifecycleOwner){
                if (it){
                    binding.swipeRefreshStock.isRefreshing = true
                    lifecycleScope.launch {
                        viewModel.getAllCategories()
                        viewModel.getAllProductByCategory(clickedCategory)
                    }
                    binding.swipeRefreshStock.isRefreshing = false
                }
            }
        }
    }

    private fun initListeners() {

        binding.swipeRefreshStock.setOnRefreshListener {
            lifecycleScope.launch {
                viewModel.getAllCategories()
                viewModel.getAllProductByCategory(clickedCategory)
            }
            binding.swipeRefreshStock.isRefreshing = false
        }

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
                    R.id.addAmount->{
                        findNavController().navigate(StockFragmentDirections.actionFragmentStockToAddAmountDialog(product.name))
                    }
                    R.id.edit -> findNavController().navigate(StockFragmentDirections.actionFragmentStockToEditProductDialog(product.name,product.id))
                    R.id.delete -> {
                        val dialog = AlertDialog.Builder(requireContext())
                            .setTitle("Удаление")
                            .setMessage("Вы действительно хотите удалить продукт ${product.name} ?")
                            .setPositiveButton("Да") { _, _ ->
                                lifecycleScope.launch {
                                    viewModel.deleteProduct(product.id)
                                    viewModel.getAllProductByCategory(clickedCategory)
                                }
                            }
                            .setNegativeButton("Нет"){ p0, _ ->
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
            popupMenu.menuInflater.inflate(R.menu.pop_up_menu_category, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.addCategory ->{
                        findNavController().navigate(StockFragmentDirections.actionFragmentStockToAddCategoryDialog())
                    }
                    R.id.editCategory -> findNavController().navigate(StockFragmentDirections.actionFragmentStockToEditCategoryDialog(clickedCategory))
                    R.id.deleteCategory -> {
                        val dialog = AlertDialog.Builder(requireContext())
                            .setTitle("Удаление")
                            .setMessage("Вы действительно хотите удалить категорию ?\n При удалений категорий все данный в категорий будут удалены")
                            .setPositiveButton("Да") { _, _ ->
                                lifecycleScope.launch {
                                    viewModel.deleteCategory(clickedCategory)
                                }
                            }
                            .setNegativeButton("Нет"){ p0, _ ->
                                p0.dismiss()
                            }
                        dialog.show()
                    }
                }
                true
            }
            popupMenu.show()
        }
    }

    private fun initVariables() {
        binding.rvStockCategory.adapter = adapterCategory
        binding.rvStockProducts.adapter = adapterProducts


        val textNoCategory = SpannableStringBuilder()
            .append("У вас нет категориев \nКатегорию можно добавить при нажатий\n")
            .bold { append("Троеточие") }
        binding.tvNoCategory.text = textNoCategory
    }
}