package com.bizlergroup.stockcontrol.presentation.ui.monitoring.purchase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bizlergroup.stockcontrol.data.models.MonitoringResponseData
import com.bizlergroup.stockcontrol.databinding.RvItemPurchaseBinding
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

class MonitoringRvPurchaseAdapter:
    ListAdapter<MonitoringResponseData, MonitoringRvPurchaseAdapter.MonitoringRvPurchaseVH>(DiffUtil) {

    inner class MonitoringRvPurchaseVH(private val binding: RvItemPurchaseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(position: Int) {
            val p = getItem(position)

            if (p.changed){
                binding.tvChanged.visibility = View.VISIBLE
                binding.tvDate.text = p.changedDate
            }

            binding.apply {
                tvNameProduct.text = p.name
                tvCountProduct.text = p.count.toString()
                tvProductType.text = p.unit
                tvDate.text = p.createdDate
                tvSalaryProduct.text = formatNumberWithThousandsSeparator(p.price)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonitoringRvPurchaseVH {
        return MonitoringRvPurchaseVH(
            RvItemPurchaseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MonitoringRvPurchaseVH, position: Int) {
        holder.setData(position)
    }

    private object DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<MonitoringResponseData>() {
        override fun areItemsTheSame(oldItem: MonitoringResponseData, newItem: MonitoringResponseData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MonitoringResponseData, newItem: MonitoringResponseData): Boolean {
            return oldItem.name == newItem.name
        }

    }

    fun formatNumberWithThousandsSeparator(number: Int): String {
        val numberFormat: NumberFormat = DecimalFormat("#,###", DecimalFormatSymbols(Locale.getDefault()))
        return numberFormat.format(number)
    }
}