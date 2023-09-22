package com.bizlergroup.stockcontrol.data.models

data class MonitoringResponseData(
    val changed : Boolean,
    val changedDate : String,
    val createdDate : String,
    val count: Int,
    val name: String,
    val price: Int,
    val unit: String
)