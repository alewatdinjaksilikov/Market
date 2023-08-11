package com.example.market.domain.usecase.login.registration.impl

import com.example.market.data.models.LoginResponseData
import com.example.market.data.models.RegistrationRequestData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.login.LoginRepository
import com.example.market.domain.usecase.login.registration.RegistrationUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegistrationUseCaseImpl @Inject constructor(private val loginRepository: LoginRepository) :
    RegistrationUseCase {

    override fun execute(body: RegistrationRequestData): Flow<ResultData<LoginResponseData?>> {
        return loginRepository.registration(body = body)
    }
}