package com.example.market.domain.usecase.login.auth.impl

import com.example.market.data.models.LoginRequestData
import com.example.market.data.models.LoginResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.login.LoginRepository
import com.example.market.domain.usecase.login.auth.AuthorizationUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthorizationUseCaseImpl @Inject constructor(private val loginRepository: LoginRepository) :
    AuthorizationUseCase {
    override fun execute(body: LoginRequestData): Flow<ResultData<LoginResponseData>> {
        return loginRepository.login(body = body)
    }
}