package com.example.market.domain.usecase.product.addProduct

import com.example.market.data.models.AddProductRequestData
import com.example.market.data.models.AddProductResponseData
import com.example.market.data.models.ResultData
import kotlinx.coroutines.flow.Flow


interface AddProductUseCase {
    fun execute(body:AddProductRequestData):Flow<ResultData<AddProductResponseData>>
}