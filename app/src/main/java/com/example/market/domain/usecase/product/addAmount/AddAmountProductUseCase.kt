package com.example.market.domain.usecase.product.addAmount

import com.example.market.data.models.AddAmountRequestData
import com.example.market.data.models.EditProductResponseData
import com.example.market.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface AddAmountProductUseCase {
    fun execute(body:AddAmountRequestData):Flow<ResultData<EditProductResponseData?>>
}