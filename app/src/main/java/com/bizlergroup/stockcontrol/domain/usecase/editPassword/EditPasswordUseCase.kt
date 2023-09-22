package com.bizlergroup.stockcontrol.domain.usecase.editPassword

import com.bizlergroup.stockcontrol.data.models.EditPasswordRequestData
import com.bizlergroup.stockcontrol.data.models.EditProductResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface EditPasswordUseCase {
    fun execute(body:EditPasswordRequestData): Flow<ResultData<EditProductResponseData>>
}