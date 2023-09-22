package com.bizlergroup.stockcontrol.presentation.ui.dialogs.listProducts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bizlergroup.stockcontrol.data.models.ProductResponseData
import com.bizlergroup.stockcontrol.databinding.RcItemListProductsBinding

class ListProductsAdapter:ListAdapter<ProductResponseData, ListProductsAdapter.ListProductsVH>(
    diffUtil
) {

    inner class ListProductsVH(private val binding : RcItemListProductsBinding):RecyclerView.ViewHolder(binding.root){
        fun setData(position:Int){
            val p = getItem(position)

            binding.tvTypeProduct.text = p.unit
            binding.tvNameProduct.text = p.name
            binding.tvAmountProduct.text = p.amount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListProductsVH {
        return ListProductsVH(
            RcItemListProductsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListProductsVH, position: Int) {
        holder.setData(position)
    }

    private object diffUtil : DiffUtil.ItemCallback<ProductResponseData>() {
        override fun areItemsTheSame(oldItem: ProductResponseData, newItem: ProductResponseData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ProductResponseData, newItem: ProductResponseData): Boolean {
            return oldItem.id == newItem.id
        }

    }
}