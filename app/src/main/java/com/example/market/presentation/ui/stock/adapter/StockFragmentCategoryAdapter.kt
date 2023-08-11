package com.example.market.presentation.ui.stock.adapter

import android.annotation.SuppressLint
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

//    private var isSelected = -1
//    private var selectedPosition = -1
//    private var lastSelectedPosition = -1
    private var clickedCategory : Boolean = false
    private var onItemClicked: ((CategoryResponseData) -> Unit)? = null
    fun setOnItemClick(block: (CategoryResponseData) -> Unit) {
        onItemClicked = block
    }

    inner class StockCategoryViewHolder(private val binding: RcItemStockCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun setData(position: Int) {
            val product = getItem(position)
//            if (position==selectedPosition&&isSelected==1){
//                binding.root.setBackgroundResource(R.drawable.bg_pressed_category)
//            }


            Glide.with(binding.root)
                .load(product.imageUrl)
                .into(binding.ivProduct)

            binding.tvProductName.text = product.name

            binding.root.setOnClickListener {
                onItemClicked?.invoke(product)
//                lastSelectedPosition = selectedPosition
//                selectedPosition = adapterPosition
//                notifyItemChanged(lastSelectedPosition)
//                notifyItemChanged(selectedPosition)
////               notifyDataSetChanged()
//
//                if (selectedPosition == adapterPosition){
//                    binding.root.setBackgroundResource(R.drawable.bg_pressed_category)
//                }else{
//                    binding.root.setBackgroundResource(R.drawable.bg_rv_stock_items)
//                }

                if (clickedCategory){
                    binding.root.setBackgroundResource(R.drawable.bg_category_pressed)
                    clickedCategory = true
                }else{
                    binding.root.setBackgroundResource(R.drawable.bg_category_pressed)
                    clickedCategory = false
                }
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

    override fun onBindViewHolder(holder: StockCategoryViewHolder, position: Int)  = holder.setData(position)

    private object diffUtil : DiffUtil.ItemCallback<CategoryResponseData>() {
        override fun areItemsTheSame(oldItem: CategoryResponseData, newItem: CategoryResponseData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CategoryResponseData, newItem: CategoryResponseData): Boolean {
            return oldItem.id == newItem.id
        }

    }
}