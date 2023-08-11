package com.example.market.domain.usecase.editPassword.impl

import com.example.market.data.models.EditPasswordRequestData
import com.example.market.data.models.EditProductResponseData
import com.example.market.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface EditPasswordUseCase {
    fun execute(body:EditPasswordRequestData): Flow<ResultData<EditProductResponseData>>
}