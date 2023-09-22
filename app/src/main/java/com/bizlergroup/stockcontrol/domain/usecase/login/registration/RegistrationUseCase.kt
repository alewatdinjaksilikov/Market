package com.bizlergroup.stockcontrol.domain.usecase.login.registration

import com.bizlergroup.stockcontrol.data.models.LoginResponseData
import com.bizlergroup.stockcontrol.data.models.RegistrationRequestData
import com.bizlergroup.stockcontrol.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface RegistrationUseCase {
    fun execute(body: RegistrationRequestData):Flow<ResultData<LoginResponseData?>>
}