package com.example.market.data.models

data class AddProductRequestData(
    val amount: Int,
    val category: String,
    val imageUrl: String,
    val name: String,
    val price: Int,
    val unit: String
)
