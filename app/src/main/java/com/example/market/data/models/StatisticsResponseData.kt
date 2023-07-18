package com.example.market.data.models

data class StatisticsResponseData(
    val products: List<StatisticsResponseProductData>,
    val sum: Int
)