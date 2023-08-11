package com.example.market.domain.usecase.login.auth

import com.example.market.data.models.LoginRequestData
import com.example.market.data.models.LoginResponseData
import com.example.market.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface AuthorizationUseCase {
    fun execute(body:LoginRequestData): Flow<ResultData<LoginResponseData?>>
}