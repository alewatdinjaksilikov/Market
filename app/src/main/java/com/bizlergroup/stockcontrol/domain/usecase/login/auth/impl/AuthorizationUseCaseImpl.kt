package com.bizlergroup.stockcontrol.domain.usecase.login.auth.impl

import com.bizlergroup.stockcontrol.data.models.LoginRequestData
import com.bizlergroup.stockcontrol.data.models.LoginResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.domain.repository.login.LoginRepository
import com.bizlergroup.stockcontrol.domain.usecase.login.auth.AuthorizationUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthorizationUseCaseImpl @Inject constructor(private val loginRepository: LoginRepository) :
    AuthorizationUseCase {
    override fun execute(body: LoginRequestData): Flow<ResultData<LoginResponseData?>>
        = loginRepository.login(body = body)
}