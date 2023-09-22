package com.bizlergroup.stockcontrol.domain.usecase.login.auth

import com.bizlergroup.stockcontrol.data.models.LoginRequestData
import com.bizlergroup.stockcontrol.data.models.LoginResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface AuthorizationUseCase {
    fun execute(body:LoginRequestData): Flow<ResultData<LoginResponseData?>>
}