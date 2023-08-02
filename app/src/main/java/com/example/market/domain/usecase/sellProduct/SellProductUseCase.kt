package com.example.market.domain.usecase.sellProduct

import com.example.market.data.models.EditProductResponseData
import com.example.market.data.models.ResultData
import com.example.market.data.models.SellProductRequestData
import kotlinx.coroutines.flow.Flow

interface SellProductUseCase {
    fun execute(body:SellProductRequestData):Flow<ResultData<EditProductResponseData>>
}