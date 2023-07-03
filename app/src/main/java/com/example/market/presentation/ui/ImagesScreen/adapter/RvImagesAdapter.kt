package com.example.market.presentation.ui.ImagesScreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.market.data.models.ImageResponseData
import com.example.market.databinding.RcItemImagesBinding

class RvImagesAdapter:ListAdapter<ImageResponseData,RvImagesAdapter.RvImagesVH>(diffUtil) {

    private var onClickImageListener: ((ImageResponseData) -> Unit)? = null
    fun onClickImage(block: (ImageResponseData) -> Unit) {
        onClickImageListener = block
    }

    inner class RvImagesVH(private val binding : RcItemImagesBinding):RecyclerView.ViewHolder(binding.root){
        fun setData(position:Int){
            val p = getItem(position)

            Glide.with(binding.root)
                .load(p.imageUrl)
                .into(binding.ivPhoto)

            binding.root.setOnClickListener {
                onClickImageListener?.invoke(p)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvImagesVH {
        return RvImagesVH(
            RcItemImagesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RvImagesVH, position: Int) {
        holder.setData(position)
    }

    private object diffUtil : DiffUtil.ItemCallback<ImageResponseData>() {
        override fun areItemsTheSame(oldItem: ImageResponseData, newItem: ImageResponseData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ImageResponseData, newItem: ImageResponseData): Boolean {
            return oldItem.id == newItem.id
        }

    }
}