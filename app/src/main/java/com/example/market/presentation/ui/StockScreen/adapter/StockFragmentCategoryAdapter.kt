package com.example.market.presentation.ui.StockScreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.market.R
import com.example.market.data.models.CategoryResponseData
import com.example.market.databinding.RcItemStockCategoryBinding

class StockFragmentCategoryAdapter:
    ListAdapter<CategoryResponseData, StockFragmentCategoryAdapter.StockCategoryViewHolder>(diffUtil) {

    private var onItemClicked: ((CategoryResponseData) -> Unit)? = null
    fun setOnItemClick(block: (CategoryResponseData) -> Unit) {
        onItemClicked = block
    }

    inner class StockCategoryViewHolder(private val binding: RcItemStockCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(position: Int) {
            val product = getItem(position)

            if (position == 1){
                binding.root.setBackgroundResource(R.drawable.bg_pressed_category)
            }

            Glide.with(binding.root)
                .load(product.imageUrl)
                .into(binding.ivProduct)

            binding.tvProductName.text = product.name

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

    private object diffUtil : DiffUtil.ItemCallback<CategoryResponseData>() {
        override fun areItemsTheSame(oldItem: CategoryResponseData, newItem: CategoryResponseData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CategoryResponseData, newItem: CategoryResponseData): Boolean {
            return oldItem.id == newItem.id
        }

    }
}