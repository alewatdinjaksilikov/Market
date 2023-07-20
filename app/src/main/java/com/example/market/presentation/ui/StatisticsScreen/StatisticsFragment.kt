package com.example.market.presentation.ui.StatisticsScreen

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.market.R
import com.example.market.databinding.FragmentStatisticsBinding
import com.example.market.presentation.vm.StatisticsFragmentViewModel
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.net.MalformedURLException
import java.net.URL
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class StatisticsFragment:Fragment(R.layout.fragment_statistics) {
    private lateinit var binding: FragmentStatisticsBinding
    private val viewModel : StatisticsFragmentViewModel by viewModels()
    val list = ArrayList<PieEntry>()

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            viewModel.getStatistics()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStatisticsBinding.bind(view)

        initListeners()
        initVariables()

        viewModel.getStatisticsFlow.onEach {


            binding.tvSalary.text = formatNumberWithThousandsSeparator(it.sum)
            if (it.products.isNotEmpty()){
                it.products.forEach { p ->
                    list.add(PieEntry(p.count.toFloat(),p.name))
                }
            }else{
                list.add(PieEntry(1f,"0"))
                list.add(PieEntry(2f,"0"))
                list.add(PieEntry(3f,"0"))
            }

            val pieDataSet = PieDataSet(list,"")

            val pieData = PieData(pieDataSet)
            pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS,500)
            pieDataSet.valueTextColor = Color.BLACK
            pieData.setValueTextSize(12f)

            binding.pieChart.data =  pieData
            binding.pieChart.description.isEnabled = false
            binding.pieChart.invalidate()
        }.launchIn(lifecycleScope)

    }

    private fun initVariables() {

    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onStop() {
        super.onStop()
        list.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        list.clear()
    }

    fun formatNumberWithThousandsSeparator(number: Int): String {
        val numberFormat: NumberFormat = DecimalFormat("#,###", DecimalFormatSymbols(Locale.getDefault()))
        return numberFormat.format(number)
    }
}

