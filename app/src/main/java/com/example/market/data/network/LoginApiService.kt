package com.example.market.data.network

import com.example.market.data.models.LoginRequestData
import com.example.market.data.models.LoginResponseData
import com.example.market.data.models.RegistrationRequestData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {

    @POST("/api/v1/auth/login")
    suspend fun login(@Body body : LoginRequestData):Response<LoginResponseData>

    @POST("/api/v1/auth/registration")
    suspend fun registration(@Body body : RegistrationRequestData): Response<LoginResponseData>
}