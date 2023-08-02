package com.example.market.domain.usecase.addProduct.impl

import com.example.market.data.models.AddProductRequestData
import com.example.market.data.models.AddProductResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.main.MainRepository
import com.example.market.domain.usecase.addProduct.AddProductUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddProductUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    AddProductUseCase {

    override fun execute(body: AddProductRequestData): Flow<ResultData<AddProductResponseData>> {
        return mainRepository.addProduct(body = body)
    }
}