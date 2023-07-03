package com.example.market.data.models

data class AddProductRequestData(
    val name:String,
    val amount:Int,
    val unit:String,
    val category : String,
    val imageUrl : String,
    val price:Int
)
