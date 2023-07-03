package com.example.market.data.models

data class EditProductRequestData(
    val name:String,
    val amount:Int,
    val category:String,
    val imageId:String,
    val price:Int,
    val unit:String
)
