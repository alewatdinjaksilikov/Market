package com.bizlergroup.stockcontrol.presentation.ui.home.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bizlergroup.stockcontrol.data.models.CategoryResponseData
import com.bizlergroup.stockcontrol.databinding.RcItemHomeBinding

class HomeScreenAdapter:ListAdapter<CategoryResponseData, HomeScreenAdapter.ProductViewHolder>(
    diffUtil
) {
    private var onItemClicked: ((CategoryResponseData) -> Unit)? = null
    fun setOnItemClick(block: (CategoryResponseData) -> Unit) {
        onItemClicked = block
    }

    inner class ProductViewHolder(private val binding:RcItemHomeBinding):RecyclerView.ViewHolder(binding.root){
        fun setData(position: Int){
            val category = getItem(position)

            Glide.with(binding.root)
                .load(category.imageUrl)
                .into(binding.ivProduct)

            binding.tvProductName.text = category.name

            binding.root.setOnClickListener {
                onItemClicked?.invoke(category)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            RcItemHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
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