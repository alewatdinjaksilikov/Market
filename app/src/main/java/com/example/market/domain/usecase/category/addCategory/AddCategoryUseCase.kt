package com.example.market.domain.usecase.category.addCategory

import com.example.market.data.models.AddCategoryRequestData
import com.example.market.data.models.AddCategoryResponseData
import com.example.market.data.models.ResultData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AddCategoryUseCase {
    fun execute(body: AddCategoryRequestData): Flow<ResultData<AddCategoryResponseData>>
}