package com.bizlergroup.stockcontrol.data.models

data class DeleteProductResponseData(
    val dateTime: String,
    val httpStatus: String,
    val message: String,
    val statusCode: Int
)