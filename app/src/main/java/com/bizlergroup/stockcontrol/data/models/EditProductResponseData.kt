package com.bizlergroup.stockcontrol.data.models

data class EditProductResponseData(
    val dateTime: String,
    val httpStatus: String,
    val message: String,
    val statusCode: Int
)