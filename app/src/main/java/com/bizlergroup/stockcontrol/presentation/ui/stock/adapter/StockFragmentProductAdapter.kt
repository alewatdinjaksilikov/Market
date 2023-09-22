package com.bizlergroup.stockcontrol.presentation.ui.stock.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bizlergroup.stockcontrol.data.models.ProductResponseData
import com.bizlergroup.stockcontrol.databinding.RcItemStockProductsBinding

class StockFragmentProductAdapter :
    ListAdapter<ProductResponseData, StockFragmentProductAdapter.StockFragmentProductViewHolder>(
        diffUtil
    ) {

    private var onPopUpMenuClickListener: ((RcItemStockProductsBinding, ProductResponseData) -> Unit)? =
        null

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): StockFragmentProductViewHolder {
        return StockFragmentProductViewHolder(
            RcItemStockProductsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: StockFragmentProductViewHolder, position: Int) {
        holder.setData(position)
    }

    inner class StockFragmentProductViewHolder(private val binding: RcItemStockProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(position: Int) {
            val product = getItem(position)

            with(binding) {
                Glide.with(binding.root).load(product.imageUrl).into(ivProduct)

                tvProductName.text = product.name
                tvAmount.text = product.amount.toString()
                tvSizeProduct.text = product.size

                btnEdit.setOnClickListener {
                    onPopUpMenuClickListener?.invoke(binding, product)
                }
            }
        }
    }

    private object diffUtil : DiffUtil.ItemCallback<ProductResponseData>() {
        override fun areItemsTheSame(
            oldItem: ProductResponseData, newItem: ProductResponseData
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ProductResponseData, newItem: ProductResponseData
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }

    fun setOnClickPopUpMenu(block: (RcItemStockProductsBinding, ProductResponseData) -> Unit) {
        onPopUpMenuClickListener = block
    }
}