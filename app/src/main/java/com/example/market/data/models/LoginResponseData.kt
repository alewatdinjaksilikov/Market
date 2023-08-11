package com.example.market.data.models

data class LoginResponseData(
    val token: String,
    val name : String,
    val surname : String,
    val phoneNumber : String
)