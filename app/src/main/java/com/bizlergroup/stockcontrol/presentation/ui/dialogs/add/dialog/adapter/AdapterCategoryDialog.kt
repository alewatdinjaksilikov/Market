package com.bizlergroup.stockcontrol.presentation.ui.dialogs.add.dialog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bizlergroup.stockcontrol.data.models.CategoryResponseData
import com.bizlergroup.stockcontrol.databinding.RvItemDialogCategoryBinding

class AdapterCategoryDialog:ListAdapter<CategoryResponseData,AdapterCategoryDialog.AdapterCategoryDialogVH>(
    DiffUtil
){
    private var onItemClicked: ((CategoryResponseData) -> Unit)? = null
    fun setOnItemClick(block: (CategoryResponseData) -> Unit) {
        onItemClicked = block
    }

    inner class AdapterCategoryDialogVH(private val binding : RvItemDialogCategoryBinding):ViewHolder(binding.root){
        fun setData(position : Int){
            val c = getItem(position)

            binding.tvNameCategory.text = c.name
            binding.root.setOnClickListener {
                onItemClicked?.invoke(c)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterCategoryDialogVH {
        return AdapterCategoryDialogVH(
            RvItemDialogCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterCategoryDialogVH, position: Int) {
        holder.setData(position)
    }

    private object DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<CategoryResponseData>() {
        override fun areItemsTheSame(oldItem: CategoryResponseData, newItem: CategoryResponseData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CategoryResponseData, newItem: CategoryResponseData): Boolean {
            return oldItem.id == newItem.id
        }

    }
}