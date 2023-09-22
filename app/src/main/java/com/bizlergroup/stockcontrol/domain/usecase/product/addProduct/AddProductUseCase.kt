package com.bizlergroup.stockcontrol.domain.usecase.product.addProduct

import com.bizlergroup.stockcontrol.data.models.AddProductRequestData
import com.bizlergroup.stockcontrol.data.models.AddProductResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import kotlinx.coroutines.flow.Flow


interface AddProductUseCase {
    fun execute(body:AddProductRequestData):Flow<ResultData<AddProductResponseData>>
}