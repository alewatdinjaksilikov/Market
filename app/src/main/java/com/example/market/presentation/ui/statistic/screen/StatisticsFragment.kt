package com.example.market.presentation.ui.statistic.screen

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.market.R
import com.example.market.databinding.FragmentStatisticsBinding
import com.example.market.presentation.ui.monitoring.adapter.MonitoringAdapter
import com.example.market.presentation.ui.statistic.vm.StatisticsFragmentViewModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

@AndroidEntryPoint
class StatisticsFragment:Fragment(R.layout.fragment_statistics) {
    private lateinit var binding: FragmentStatisticsBinding
    private val viewModel : StatisticsFragmentViewModel by viewModels()

    private lateinit var adapter : MonitoringAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStatisticsBinding.bind(view)

        initVariables()
        initListeners()
        initObservables()

    }

    private fun initVariables() {
        val fragmentManager = requireActivity().supportFragmentManager
        adapter = MonitoringAdapter(fragmentManager,lifecycle)
        binding.viewPager.adapter = adapter

        viewModel.getStatistics()
    }

    private fun initObservables() {
        viewModel.getStatisticsFlow.onEach {
            binding.tvRemainder.text = formatNumberWithThousandsSeparator(it.amount)
            binding.tvPrice.text = formatNumberWithThousandsSeparator(it.price)
            binding.tvPriceMonitoring.text = formatNumberWithThousandsSeparator(it.price)
        }.launchIn(lifecycleScope)
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewPager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })
    }

    fun formatNumberWithThousandsSeparator(number: Int): String {
        val numberFormat: NumberFormat = DecimalFormat("#,###", DecimalFormatSymbols(Locale.getDefault()))
        return numberFormat.format(number)
    }
}

