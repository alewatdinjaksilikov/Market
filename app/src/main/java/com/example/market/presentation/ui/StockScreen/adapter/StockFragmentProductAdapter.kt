package com.example.market.presentation.ui.StockScreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.market.data.models.ProductResponseData
import com.example.market.databinding.RcItemStockProductsBinding

class StockFragmentProductAdapter:ListAdapter<ProductResponseData,StockFragmentProductAdapter.StockFragmentProductViewHolder>(diffUtil) {

    private var onPopUpMenuClickListener:((RcItemStockProductsBinding,ProductResponseData)->Unit)? = null
    fun setOnClickPopUpMenu(block:(RcItemStockProductsBinding,ProductResponseData)->Unit){
        onPopUpMenuClickListener = block
    }

    inner class StockFragmentProductViewHolder(private val binding:RcItemStockProductsBinding):RecyclerView.ViewHolder(binding.root){
        fun setData(position:Int){
            val product = getItem(position)

            Glide.with(binding.root)
                .load(product.imageUrl)
                .into(binding.ivProduct)

            binding.apply {
                tvProductName.text = product.name
                tvAmount.text = product.amount.toString()
                tvSizeProduct.text = 0.toString()
            }

            binding.btnEdit.setOnClickListener {
                onPopUpMenuClickListener?.invoke(binding,product)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StockFragmentProductViewHolder {
        return StockFragmentProductViewHolder(
            RcItemStockProductsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StockFragmentProductViewHolder, position: Int) {
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