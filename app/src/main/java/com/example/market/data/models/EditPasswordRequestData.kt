package com.example.market.data.models

data class EditPasswordRequestData(
    val currentPass: String,
    val newPass: String
)