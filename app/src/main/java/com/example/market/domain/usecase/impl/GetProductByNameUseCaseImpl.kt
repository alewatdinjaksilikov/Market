package com.example.market.domain.usecase.impl

import com.example.market.data.models.ProductResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.MainRepository
import com.example.market.domain.usecase.GetProductByNameUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductByNameUseCaseImpl @Inject constructor(
    private val mainRepository: MainRepository
):GetProductByNameUseCase {
    override fun execute(name: String): Flow<ResultData<ProductResponseData>> {
        return mainRepository.getProductByName(name = name)
    }
}