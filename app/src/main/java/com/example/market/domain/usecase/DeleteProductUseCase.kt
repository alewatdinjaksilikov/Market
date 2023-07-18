package com.example.market.domain.usecase

import com.example.market.data.models.DeleteProductResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface DeleteProductUseCase {
    fun execute(id:Int):Flow<ResultData<DeleteProductResponseData>>
}