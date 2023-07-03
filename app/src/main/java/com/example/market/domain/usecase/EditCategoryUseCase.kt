package com.example.market.domain.usecase

import com.example.market.data.models.AddCategoryRequestData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface EditCategoryUseCase {
    suspend fun execute(body:AddCategoryRequestData,id:Int):Flow<ResultData<Any>>
}