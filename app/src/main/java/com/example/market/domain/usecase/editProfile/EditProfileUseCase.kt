package com.example.market.domain.usecase.editProfile

import com.example.market.data.models.EditProfileRequestData
import com.example.market.data.models.LoginResponseData
import com.example.market.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface EditProfileUseCase {
    fun execute(body:EditProfileRequestData):Flow<ResultData<LoginResponseData>>
}