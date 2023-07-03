package com.example.market.presentation.ui.HomeScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.market.databinding.RcItemHomeShimmerBinding

class ShimmerLayoutAdapter(private val itemCount: Int) : RecyclerView.Adapter<ShimmerLayoutAdapter.ShimmerViewHolder>() {


    inner class ShimmerViewHolder(private val binding:RcItemHomeShimmerBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShimmerViewHolder {
        return ShimmerViewHolder(
            RcItemHomeShimmerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShimmerViewHolder, position: Int) {
        // Ничего не делаем, так как это элемент с эффектом Shimmer
    }

    override fun getItemCount(): Int {
        return itemCount
    }

}