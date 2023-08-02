package com.example.market.domain.usecase.getProductByName

import com.example.market.data.models.ProductResponseData
import com.example.market.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface GetProductByNameUseCase {
    fun execute(name:String): Flow<ResultData<ProductResponseData>>
}