package com.example.market.data.models

data class AddProductRequestData(
    val amount: Int,
    val category: String,
    val name: String,
    val price: Int,
    val size: String,
    val unit: String
)
