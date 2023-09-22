package com.bizlergroup.stockcontrol.domain.usecase.login.registration.impl

import com.bizlergroup.stockcontrol.data.models.LoginResponseData
import com.bizlergroup.stockcontrol.data.models.RegistrationRequestData
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.domain.repository.login.LoginRepository
import com.bizlergroup.stockcontrol.domain.usecase.login.registration.RegistrationUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegistrationUseCaseImpl @Inject constructor(private val loginRepository: LoginRepository) :
    RegistrationUseCase {

    override fun execute(body: RegistrationRequestData): Flow<ResultData<LoginResponseData?>>
        = loginRepository.registration(body = body)
}