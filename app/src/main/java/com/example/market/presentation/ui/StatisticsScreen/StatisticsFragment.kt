package com.example.market.presentation.ui.StatisticsScreen

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.market.R
import com.example.market.databinding.FragmentStatisticsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticsFragment:Fragment(R.layout.fragment_statistics) {
    private lateinit var binding: FragmentStatisticsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStatisticsBinding.bind(view)

        initListeners()
        initVariables()
//
//        val list = ArrayList<PieEntry>()
//        list.add(PieEntry(205f,"2012"))
//        list.add(PieEntry(200f,"2013"))
//        list.add(PieEntry(500f,"2015"))
//        list.add(PieEntry(521f,"2016"))
//
//        val pieDataSet = PieDataSet(list,"Jillar")
//
//        val pieData = PieData(pieDataSet)
//        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS,500)
//        pieDataSet.valueTextColor = Color.BLACK
//        pieData.setValueTextSize(12f)
//
//        binding.pieChart.data =  pieData
//        binding.pieChart.description.isEnabled = false
//        binding.pieChart.invalidate()
//


    }

    private fun initVariables() {

    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}