package com.example.market.presentation.ui.monitoring

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.market.presentation.ui.monitoring.purchase.PurchaseFragment
import com.example.market.presentation.ui.monitoring.sales.SalesFragment

class MonitoringAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        if (position == 1){
            return SalesFragment()
        }
        return PurchaseFragment()
    }

}