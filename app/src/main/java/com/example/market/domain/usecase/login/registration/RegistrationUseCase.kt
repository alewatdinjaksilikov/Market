package com.example.market.domain.usecase.login.registration

import com.example.market.data.models.LoginResponseData
import com.example.market.data.models.RegistrationRequestData
import com.example.market.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface RegistrationUseCase {
    fun execute(body: RegistrationRequestData):Flow<ResultData<LoginResponseData?>>
}