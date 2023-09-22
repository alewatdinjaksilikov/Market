package com.bizlergroup.stockcontrol.data.models

data class LoginResponseData(
    val token: String,
    val name : String,
    val surname : String,
    val phoneNumber : String
)