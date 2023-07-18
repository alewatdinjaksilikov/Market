package com.example.market.presentation.ui.monitoring.sales

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.market.data.models.MonitoringResponseData
import com.example.market.databinding.RvItemPurchaseBinding
import com.example.market.databinding.RvItemSalesBinding

class MonitoringRvSalesAdapter:ListAdapter<MonitoringResponseData,MonitoringRvSalesAdapter.MonitoringRvSaleVH>(diffUtil) {

    inner class MonitoringRvSaleVH(private val binding: RvItemSalesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(position: Int) {
            val p = getItem(position)

            binding.apply {
                tvNameProduct.text = p.name
                tvCountProduct.text = p.count.toString()
                tvProductType.text = p.unit
                tvSalaryProduct.text = p.price.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonitoringRvSaleVH {
        return MonitoringRvSaleVH(
            RvItemSalesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MonitoringRvSaleVH, position: Int) {
        holder.setData(position)
    }

    private object diffUtil : DiffUtil.ItemCallback<MonitoringResponseData>() {
        override fun areItemsTheSame(oldItem: MonitoringResponseData, newItem: MonitoringResponseData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MonitoringResponseData, newItem: MonitoringResponseData): Boolean {
            return oldItem.name == newItem.name
        }

    }
}