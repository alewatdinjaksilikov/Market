package com.example.market.domain.usecase.impl

import com.example.market.data.models.AddProductRequestData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.MainRepository
import com.example.market.domain.usecase.AddProductUseCase
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class AddProductUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):AddProductUseCase {

    override fun execute(body: AddProductRequestData): Flow<ResultData<Any>> {
        return mainRepository.addProduct(body = body)
    }
}