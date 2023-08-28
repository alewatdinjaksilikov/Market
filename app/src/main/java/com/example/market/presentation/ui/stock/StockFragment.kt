package com.example.market.presentation.ui.stock

import android.app.AlertDialog
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.PopupMenu
import androidx.core.text.bold
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.market.R
import com.example.market.data.models.CategoryResponseData
import com.example.market.data.models.DeleteProductResponseData
import com.example.market.data.models.EditProductResponseData
import com.example.market.data.models.ProductResponseData
import com.example.market.databinding.FragmentStockBinding
import com.example.market.presentation.ui.stock.adapter.StockFragmentCategoryAdapter
import com.example.market.presentation.ui.stock.adapter.StockFragmentProductAdapter
import com.example.market.presentation.ui.stock.vm.StockFragmentViewModel
import com.example.market.utils.AddAmountClick
import com.example.market.utils.AddButtonCategoryClick
import com.example.market.utils.AddButtonClick
import com.example.market.utils.Constants
import com.example.market.utils.EditCategoryClick
import com.example.market.utils.EditProductClick
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StockFragment : Fragment(R.layout.fragment_stock) {

    private lateinit var binding: FragmentStockBinding

    private val viewModel: StockFragmentViewModel by viewModels()

    private var adapterCategory = StockFragmentCategoryAdapter()
    private var adapterProducts = StockFragmentProductAdapter()
    private var clickedCategoryId = Constants.UNDEFINED_ID

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

    private fun initVariables() {
        initCategoryAndProductRecyclerViewAdapter()

        val textNoCategory =
            SpannableStringBuilder().append("У вас нет категориев \nКатегорию можно добавить при нажатий\n")
                .bold { append("Троеточие") }

        binding.tvNoCategory.text = textNoCategory
    }

    private fun initObservables() {
        viewModel.deleteCategoryFlow.onEach {
            setSwipeRefreshStockTrue()

            if (isDeleteCategoryStatus202(it)) {
                lifecycleScope.launch {
                    viewModel.getAllCategories()
                    viewModel.getAllProductByCategory(clickedCategoryId)
                }
                setSwipeRefreshStockFalse()
            }
        }.launchIn(lifecycleScope)
        viewModel.deleteProductFlow.onEach {
            setSwipeRefreshStockTrue()

            if (isDeleteProductStatus202(it)) {
                lifecycleScope.launch {
                    viewModel.getAllCategories()
                    viewModel.getAllProductByCategory(clickedCategoryId)
                }
                setSwipeRefreshStockFalse()
            }
        }.launchIn(lifecycleScope)
        viewModel.getAllCategoriesFlow.onEach {
            if (it.isNotEmpty()) {
                initCategoryAdapterList(it)
                stopAndInvisibleShimmerCategory()

                binding.tvNoCategory.visibility = View.INVISIBLE

                clickedCategoryId = it.first().id
                viewModel.getAllProductByCategory(clickedCategoryId)
            } else {
                stopAndInvisibleShimmerCategory()
                stopAndInvisibleShimmerProduct()

                binding.tvNoCategory.visibility = View.VISIBLE
            }
        }.launchIn(lifecycleScope)
        viewModel.getAllProduct.onEach {
            if (it.isNotEmpty()) {
                initProductAdapterList(it)
                stopAndInvisibleShimmerProduct()

                binding.tvNoProducts.visibility = View.INVISIBLE
            } else {
                stopAndInvisibleShimmerProduct()

                binding.tvNoProducts.visibility = View.VISIBLE
            }
        }.launchIn(lifecycleScope)

        lifecycleScope.launch {
            AddButtonClick.buttonClickLiveData.observe(viewLifecycleOwner) {
                if (it) {
                    setSwipeRefreshStockTrue()
                    lifecycleScope.launch {
                        viewModel.getAllCategories()
                        viewModel.getAllProductByCategory(clickedCategoryId)
                    }
                    setSwipeRefreshStockFalse()
                }
            }
            AddButtonCategoryClick.buttonAddCategoryLiveData.observe(viewLifecycleOwner) {
                if (it) {
                    setSwipeRefreshStockTrue()
                    lifecycleScope.launch {
                        viewModel.getAllCategories()
                        viewModel.getAllProductByCategory(clickedCategoryId)
                    }
                    setSwipeRefreshStockFalse()
                }
            }
            EditProductClick.buttonEditProductLiveData.observe(viewLifecycleOwner) {
                if (it) {
                    setSwipeRefreshStockTrue()
                    lifecycleScope.launch {
                        viewModel.getAllCategories()
                        viewModel.getAllProductByCategory(clickedCategoryId)
                    }
                    setSwipeRefreshStockFalse()
                }
            }
            EditCategoryClick.buttonEditCategoryLiveData.observe(viewLifecycleOwner) {
                if (it) {
                    setSwipeRefreshStockTrue()
                    lifecycleScope.launch {
                        viewModel.getAllCategories()
                        viewModel.getAllProductByCategory(clickedCategoryId)
                    }
                    setSwipeRefreshStockFalse()
                }
            }
            AddAmountClick.buttonAddAmountLiveData.observe(viewLifecycleOwner) {
                if (it) {
                    setSwipeRefreshStockTrue()
                    lifecycleScope.launch {
                        viewModel.getAllCategories()
                        viewModel.getAllProductByCategory(clickedCategoryId)
                    }
                    setSwipeRefreshStockFalse()
                }
            }
        }
    }

    private fun initListeners() {
        binding.swipeRefreshStock.setOnRefreshListener {
            lifecycleScope.launch {
                viewModel.getAllCategories()
                viewModel.getAllProductByCategory(clickedCategoryId)
            }
            binding.swipeRefreshStock.isRefreshing = false
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnMenuCategory.setOnClickListener {
            val popupMenu = PopupMenu(requireContext(), binding.btnMenuCategory)
            popupMenu.menuInflater.inflate(R.menu.pop_up_menu_category, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.addCategory -> {
                        findNavController().navigate(StockFragmentDirections.actionFragmentStockToAddCategoryDialog())
                    }

                    R.id.editCategory -> findNavController().navigate(
                        StockFragmentDirections.actionFragmentStockToEditCategoryDialog(
                            clickedCategoryId
                        )
                    )

                    R.id.deleteCategory -> {
                        val dialog = AlertDialog.Builder(requireContext()).setTitle("Удаление")
                            .setMessage("Вы действительно хотите удалить категорию ?\n При удалений категорий все данный в категорий будут удалены")
                            .setPositiveButton("Да") { _, _ ->
                                lifecycleScope.launch {
                                    viewModel.deleteCategory(clickedCategoryId)
                                }
                            }.setNegativeButton("Нет") { p0, _ ->
                                p0.dismiss()
                            }
                        dialog.show()
                    }
                }
                true
            }
            popupMenu.show()
        }

        adapterCategory.setOnItemClick {
            clickedCategoryId = it.id
            lifecycleScope.launch {
                viewModel.getAllProductByCategory(clickedCategoryId)
            }
        }
        adapterProducts.setOnClickPopUpMenu { binding, product ->
            val popupMenu = PopupMenu(requireContext(), binding.btnEdit)
            popupMenu.menuInflater.inflate(R.menu.pop_up_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.addAmount -> {
                        findNavController().navigate(
                            StockFragmentDirections.actionFragmentStockToAddAmountDialog(
                                product.name
                            )
                        )
                    }

                    R.id.edit -> findNavController().navigate(
                        StockFragmentDirections.actionFragmentStockToEditProductDialog(
                            product.name, product.id
                        )
                    )

                    R.id.delete -> {
                        val dialog = AlertDialog.Builder(requireContext()).setTitle("Удаление")
                            .setMessage("Вы действительно хотите удалить продукт ${product.name} ?")
                            .setPositiveButton("Да") { _, _ ->
                                lifecycleScope.launch {
                                    viewModel.deleteProduct(product.id)
                                    viewModel.getAllProductByCategory(clickedCategoryId)
                                }
                            }.setNegativeButton("Нет") { p0, _ ->
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

    private fun setSwipeRefreshStockTrue() {
        binding.swipeRefreshStock.isRefreshing = true
    }

    private fun setSwipeRefreshStockFalse() {
        binding.swipeRefreshStock.isRefreshing = false
    }

    private fun initCategoryAndProductRecyclerViewAdapter() {
        with(binding) {
            rvStockCategory.adapter = adapterCategory
            rvStockProducts.adapter = adapterProducts
        }
    }

    private fun isDeleteProductStatus202(it: DeleteProductResponseData): Boolean {
        return it.statusCode == 202
    }

    private fun isDeleteCategoryStatus202(it: EditProductResponseData): Boolean {
        return it.statusCode == 202
    }

    private fun initProductAdapterList(it: List<ProductResponseData>) {
        adapterProducts.submitList(it)
    }

    private fun initCategoryAdapterList(it: List<CategoryResponseData>) {
        adapterCategory.submitList(it)
    }

    private fun stopAndInvisibleShimmerCategory() {
            with(binding) {
                shimmerStockCategory.stopShimmer()
                shimmerStockCategory.visibility = View.INVISIBLE
            }

    }

    private fun stopAndInvisibleShimmerProduct() {
            with(binding) {
                shimmerStockProducts.stopShimmer()
                shimmerStockProducts.visibility = View.INVISIBLE
            }

    }
}