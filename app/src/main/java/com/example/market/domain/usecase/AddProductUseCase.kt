package com.example.market.domain.usecase

import com.example.market.data.models.AddProductRequestData
import com.example.market.data.models.AddProductResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.MainRepository
import dagger.Provides
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface AddProductUseCase {
    fun execute(body:AddProductRequestData):Flow<ResultData<AddProductResponseData>>
}