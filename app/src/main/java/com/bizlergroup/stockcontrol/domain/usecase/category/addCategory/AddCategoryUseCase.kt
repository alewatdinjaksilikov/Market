package com.bizlergroup.stockcontrol.domain.usecase.category.addCategory

import com.bizlergroup.stockcontrol.data.models.AddCategoryRequestData
import com.bizlergroup.stockcontrol.data.models.AddCategoryResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface AddCategoryUseCase {
    fun execute(body: AddCategoryRequestData): Flow<ResultData<AddCategoryResponseData>>
}