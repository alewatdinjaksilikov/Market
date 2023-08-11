package com.example.market.domain.repository.login

import com.example.market.data.models.LoginRequestData
import com.example.market.data.models.LoginResponseData
import com.example.market.data.models.RegistrationRequestData
import com.example.market.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun login(body:LoginRequestData): Flow<ResultData<LoginResponseData?>>

    fun registration(body:RegistrationRequestData):Flow<ResultData<LoginResponseData?>>
}