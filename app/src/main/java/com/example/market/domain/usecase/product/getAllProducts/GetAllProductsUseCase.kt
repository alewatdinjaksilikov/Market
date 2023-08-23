package com.example.market.domain.usecase.product.getAllProducts

import com.example.market.data.models.ProductResponseData
import com.example.market.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface GetAllProductsUseCase {
    fun execute():Flow<ResultData<List<ProductResponseData>>>
}