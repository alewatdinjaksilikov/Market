package com.example.market.domain.usecase.editCategory

import com.example.market.data.models.AddCategoryRequestData
import com.example.market.data.models.EditProductResponseData
import com.example.market.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface EditCategoryUseCase {
    fun execute(body:AddCategoryRequestData,id:Int):Flow<ResultData<EditProductResponseData>>
}