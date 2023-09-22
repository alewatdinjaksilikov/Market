package com.bizlergroup.stockcontrol.domain.usecase.editProfile

import com.bizlergroup.stockcontrol.data.models.EditProfileRequestData
import com.bizlergroup.stockcontrol.data.models.LoginResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface EditProfileUseCase {
    fun execute(body:EditProfileRequestData):Flow<ResultData<LoginResponseData>>
}