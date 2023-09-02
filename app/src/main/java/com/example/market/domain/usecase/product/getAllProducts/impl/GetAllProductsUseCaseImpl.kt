package com.example.market.domain.usecase.product.getAllProducts.impl

import com.example.market.data.models.ProductResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.main.MainRepository
import com.example.market.domain.usecase.product.getAllProducts.GetAllProductsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductsUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    GetAllProductsUseCase {
    override fun execute(): Flow<ResultData<List<ProductResponseData>>>
        = mainRepository.getAllProducts()
}