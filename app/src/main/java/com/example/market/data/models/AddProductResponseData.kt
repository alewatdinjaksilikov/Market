package com.example.market.data.models

data class AddProductResponseData(
    val message: String,
    val statusCode: Int,
    val httpStatus: String,
    val dateTime: String
)