package com.example.market.domain.usecase.impl

import com.example.market.data.models.ProductResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.MainRepository
import com.example.market.domain.usecase.GetAllProductsByCategoryUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductsByCategoryUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :GetAllProductsByCategoryUseCase{
    override fun execute(id: Int): Flow<ResultData<List<ProductResponseData>>> {
        return mainRepository.getAllProductByCategory(id = id)
    }
}