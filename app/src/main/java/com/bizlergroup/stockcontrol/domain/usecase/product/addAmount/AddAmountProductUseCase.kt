package com.bizlergroup.stockcontrol.domain.usecase.product.addAmount

import com.bizlergroup.stockcontrol.data.models.AddAmountRequestData
import com.bizlergroup.stockcontrol.data.models.EditProductResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface AddAmountProductUseCase {
    fun execute(body:AddAmountRequestData):Flow<ResultData<EditProductResponseData?>>
}