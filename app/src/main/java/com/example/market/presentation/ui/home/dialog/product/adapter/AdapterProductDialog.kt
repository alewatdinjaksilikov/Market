package com.example.market.presentation.ui.home.dialog.product.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.market.data.models.ProductResponseData
import com.example.market.databinding.RvItemDialogProductBinding

class AdapterProductDialog:ListAdapter<ProductResponseData,AdapterProductDialog.AdapterProductDialogVH>(diffUtil) {

    private var onItemClicked: ((ProductResponseData) -> Unit)? = null
    fun setOnItemClick(block: (ProductResponseData) -> Unit) {
        onItemClicked = block
    }

    inner class AdapterProductDialogVH(private val binding:RvItemDialogProductBinding):ViewHolder(binding.root){
        fun setData(position: Int){
            val p = getItem(position)

            binding.tvNameProduct.text = p.name

            binding.root.setOnClickListener {
                onItemClicked?.invoke(p)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterProductDialogVH {
        return AdapterProductDialogVH(
            RvItemDialogProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterProductDialogVH, position: Int) {
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