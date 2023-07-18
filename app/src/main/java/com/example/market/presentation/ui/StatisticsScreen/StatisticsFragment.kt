package com.example.market.presentation.ui.StatisticsScreen

import android.content.Context
import android.content.pm.ActivityInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.market.R
import com.example.market.databinding.FragmentStatisticsBinding
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import dagger.hilt.android.AndroidEntryPoint
import java.net.MalformedURLException
import java.net.URL

@AndroidEntryPoint
class StatisticsFragment:Fragment(R.layout.fragment_statistics) {
    private lateinit var binding: FragmentStatisticsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStatisticsBinding.bind(view)

        initListeners()
        initVariables()

        val list = ArrayList<PieEntry>()
        list.add(PieEntry(205f,"2012"))
        list.add(PieEntry(200f,"2013"))
        list.add(PieEntry(500f,"2015"))
        list.add(PieEntry(521f,"2016"))

        val pieDataSet = PieDataSet(list,"Jillar")

        val pieData = PieData(pieDataSet)
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS,500)
        pieDataSet.valueTextColor = Color.BLACK
        pieData.setValueTextSize(12f)

        binding.pieChart.data =  pieData
        binding.pieChart.description.isEnabled = false
        binding.pieChart.invalidate()
//


    }

    private fun initVariables() {

    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private inner class MyWebViewClient:WebViewClient(){
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            extractDomainFromUrl(url)
        }

        private fun extractDomainFromUrl(url: String?) {
            try {
                val parsedUrl = URL(url)
                val domain = parsedUrl.host
                if (domain != null) {
                    // Используйте полученное доменное имя в вашем приложении
                    Log.d("DOMAIN", domain)
                }
            }catch (e: MalformedURLException){
                e.printStackTrace()
            }
        }
    }
}

