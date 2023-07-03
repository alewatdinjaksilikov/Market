package com.example.market.presentation.ui.StockScreen

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.market.R
import com.example.market.databinding.FragmentStockBinding
import com.example.market.presentation.ui.StockScreen.adapter.StockFragmentCategoryAdapter
import com.example.market.presentation.ui.StockScreen.adapter.StockFragmentProductAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StockFragment : Fragment(R.layout.fragment_stock) {
    private lateinit var binding: FragmentStockBinding
    private var adapterCategory = StockFragmentCategoryAdapter()
    private var adapterProducts = StockFragmentProductAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStockBinding.bind(view)



        initVariables()
        initListeners()
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        adapterProducts.setOnClickPopUpMenu { binding->
            val popupMenu = PopupMenu(requireContext(), binding.btnEdit)
            popupMenu.menuInflater.inflate(R.menu.pop_up_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.edit -> findNavController().navigate(StockFragmentDirections.actionFragmentStockToEditProductDialog())
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