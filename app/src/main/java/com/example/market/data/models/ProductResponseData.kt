package com.example.market.data.models

data class ProductResponseData(
    val id:Int,
    val name:String,
    val amount:Int,
    val category : String,
    val imageId : String,
    val unit : String,
    val price : Int,
    val dateTime : String
)
