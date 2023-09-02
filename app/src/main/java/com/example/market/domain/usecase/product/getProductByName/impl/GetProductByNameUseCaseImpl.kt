package com.example.market.domain.usecase.product.getProductByName.impl

import com.example.market.data.models.ProductResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.main.MainRepository
import com.example.market.domain.usecase.product.getProductByName.GetProductByNameUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductByNameUseCaseImpl @Inject constructor(
    private val mainRepository: MainRepository
): GetProductByNameUseCase {
    override fun execute(name: String): Flow<ResultData<ProductResponseData>>
        = mainRepository.getProductByName(name = name)
}