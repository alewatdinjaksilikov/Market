package com.example.market.presentation.ui.StockScreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.market.data.models.Product
import com.example.market.databinding.RcItemStockCategoryBinding

class StockFragmentCategoryAdapter() :
    ListAdapter<Product, StockFragmentCategoryAdapter.StockCategoryViewHolder>(diffUtil) {

    private var onItemClicked: ((Product) -> Unit)? = null
    fun setOnItemClick(block: (Product) -> Unit) {
        onItemClicked = block
    }

    inner class StockCategoryViewHolder(private val binding: RcItemStockCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(position: Int) {
            val product = getItem(position)

            binding.root.setOnClickListener {
                onItemClicked?.invoke(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockCategoryViewHolder {
        return StockCategoryViewHolder(
            RcItemStockCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StockCategoryViewHolder, position: Int) {
        holder.setData(position)
    }

    private object diffUtil : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

    }
}