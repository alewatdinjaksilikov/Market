package com.example.market.presentation.ui.monitoring.sales

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.market.R
import com.example.market.databinding.FragmentSalesBinding
import com.example.market.presentation.ui.monitoring.vm.MonitoringFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SalesFragment : Fragment() {
    private lateinit var binding:FragmentSalesBinding
    private var adapter = MonitoringRvSalesAdapter()
    private val viewModel : MonitoringFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sales, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSalesBinding.bind(view)

        initVariables()
        initObservables()
        initListeners()
    }

    private fun initListeners() {
        binding.swipeRefreshSales.setOnRefreshListener {
            initVariables()
            binding.swipeRefreshSales.isRefreshing = false
        }
    }

    private fun initObservables() {
        viewModel.getAllSaleFlow.onEach {
            if (it.isEmpty()){
                binding.tvNoSales.visibility = View.VISIBLE
            }
            adapter.submitList(it.asReversed())
        }.launchIn(lifecycleScope)
    }

    private fun initVariables() {
        binding.rvSales.adapter = adapter
        lifecycleScope.launch {
            viewModel.getAllSale()
        }
    }

}