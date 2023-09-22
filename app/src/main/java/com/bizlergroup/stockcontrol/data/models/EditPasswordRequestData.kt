package com.bizlergroup.stockcontrol.data.models

data class EditPasswordRequestData(
    val currentPass: String,
    val newPass: String
)