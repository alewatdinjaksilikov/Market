package com.bizlergroup.stockcontrol.domain.repository.login

import com.bizlergroup.stockcontrol.data.models.LoginRequestData
import com.bizlergroup.stockcontrol.data.models.LoginResponseData
import com.bizlergroup.stockcontrol.data.models.RegistrationRequestData
import com.bizlergroup.stockcontrol.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun login(body:LoginRequestData): Flow<ResultData<LoginResponseData?>>

    fun registration(body:RegistrationRequestData):Flow<ResultData<LoginResponseData?>>
}