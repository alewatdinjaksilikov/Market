package com.example.market.presentation.ui.StockScreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.market.data.models.Product
import com.example.market.databinding.RcItemStockProductsBinding

class StockFragmentProductAdapter:ListAdapter<Product,StockFragmentProductAdapter.StockFragmentProductViewHolder>(diffUtil) {

    private var onPopUpMenuClickListener:((RcItemStockProductsBinding)->Unit)? = null
    fun setOnClickPopUpMenu(block:(RcItemStockProductsBinding)->Unit){
        onPopUpMenuClickListener = block
    }

    inner class StockFragmentProductViewHolder(private val binding:RcItemStockProductsBinding):RecyclerView.ViewHolder(binding.root){
        fun setData(position:Int){
            val product = getItem(position)

            binding.btnEdit.setOnClickListener {
                onPopUpMenuClickListener?.invoke(binding)
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


    private object diffUtil : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

    }
}