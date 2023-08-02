package com.example.market.domain.usecase.getAllProductsByCategory

import com.example.market.data.models.ProductResponseData
import com.example.market.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface GetAllProductsByCategoryUseCase{
    fun execute(id:Int): Flow<ResultData<List<ProductResponseData>>>
}