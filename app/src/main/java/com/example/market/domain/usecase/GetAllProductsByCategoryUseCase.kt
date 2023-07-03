package com.example.market.domain.usecase

import com.example.market.data.models.Product
import com.example.market.data.models.ResultData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface GetAllProductsByCategoryUseCase{
    suspend fun execute(id:Int): Flow<ResultData<List<Product>>>
}