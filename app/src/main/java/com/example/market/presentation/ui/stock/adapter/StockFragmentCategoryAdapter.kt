package com.example.market.presentation.ui.stock.adapter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.market.data.models.CategoryResponseData
import com.example.market.databinding.RcItemStockCategoryBinding

class StockFragmentCategoryAdapter :
    ListAdapter<CategoryResponseData, StockFragmentCategoryAdapter.StockCategoryViewHolder>(diffUtil) {

    private var clickedCategory = 0 // default first item selected
    private var onItemClicked: ((CategoryResponseData) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockCategoryViewHolder {
        return StockCategoryViewHolder(
            RcItemStockCategoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: StockCategoryViewHolder, position: Int) =
        holder.setData(position)

    inner class StockCategoryViewHolder(private val binding: RcItemStockCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun setData(position: Int) {
            val product = getItem(position)

            with(binding) {
                Glide.with(root).load(product.imageUrl).into(ivProduct)
                tvProductName.text = product.name

                if (clickedCategory == position)
                    categoryConatiner.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#FFCC00")))
                else
                    categoryConatiner.setStrokeColor(ColorStateList.valueOf(Color.TRANSPARENT))

                root.setOnClickListener {
                    onItemClicked?.invoke(product)
                    notifyItemChanged(clickedCategory)
                    clickedCategory = position
                    notifyItemChanged(clickedCategory)

                }
            }
        }
    }

    private object diffUtil : DiffUtil.ItemCallback<CategoryResponseData>() {
        override fun areItemsTheSame(
            oldItem: CategoryResponseData, newItem: CategoryResponseData
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: CategoryResponseData, newItem: CategoryResponseData
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }

    fun setOnItemClick(block: (CategoryResponseData) -> Unit) {
        onItemClicked = block
    }
}