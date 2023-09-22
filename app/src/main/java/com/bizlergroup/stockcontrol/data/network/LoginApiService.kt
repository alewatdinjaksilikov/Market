package com.bizlergroup.stockcontrol.data.network

import com.bizlergroup.stockcontrol.data.models.LoginRequestData
import com.bizlergroup.stockcontrol.data.models.LoginResponseData
import com.bizlergroup.stockcontrol.data.models.RegistrationRequestData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {

    @POST("/api/v1/auth/login")
    suspend fun login(@Body body : LoginRequestData):Response<LoginResponseData>

    @POST("/api/v1/auth/registration")
    suspend fun registration(@Body body : RegistrationRequestData): Response<LoginResponseData>
}