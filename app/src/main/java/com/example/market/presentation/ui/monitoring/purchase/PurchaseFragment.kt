package com.example.market.presentation.ui.monitoring.purchase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.market.R
import com.example.market.databinding.FragmentPurchaseBinding
import com.example.market.presentation.ui.monitoring.vm.MonitoringFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PurchaseFragment : Fragment() {
    private lateinit var binding: FragmentPurchaseBinding
    private var adapter = MonitoringRvPurchaseAdapter()
    private val viewModel : MonitoringFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_purchase, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPurchaseBinding.bind(view)

        initVariables()
        initObservables()
        initListeners()
    }

    private fun initListeners() {
        binding.swipeRefreshPurchase.setOnRefreshListener {
            initVariables()
            binding.swipeRefreshPurchase.isRefreshing = false
        }
    }

    private fun initObservables() {
        viewModel.getAllBuyFlow.onEach {
            if (it.isEmpty()){
                binding.tvNoPurchase.visibility = View.VISIBLE
            }
            adapter.submitList(it.asReversed())
        }.launchIn(lifecycleScope)
    }

    private fun initVariables() {
        binding.rvPurchase.adapter = adapter
        lifecycleScope.launch {
            viewModel.getAllBuy()
        }
    }
}