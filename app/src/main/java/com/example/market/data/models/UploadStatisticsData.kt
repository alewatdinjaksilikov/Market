package com.example.market.data.models

data class UploadStatisticsData(
    val description: String,
    val filename: String,
    val inputStream: InputStream,
    val `open`: Boolean,
    val readable: Boolean,
    val uri: String,
    val url: String
)