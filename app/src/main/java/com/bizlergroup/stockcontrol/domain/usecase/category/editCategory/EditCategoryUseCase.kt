package com.bizlergroup.stockcontrol.domain.usecase.category.editCategory

import com.bizlergroup.stockcontrol.data.models.AddCategoryRequestData
import com.bizlergroup.stockcontrol.data.models.EditProductResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface EditCategoryUseCase {
    fun execute(body:AddCategoryRequestData,id:Int):Flow<ResultData<EditProductResponseData>>
}