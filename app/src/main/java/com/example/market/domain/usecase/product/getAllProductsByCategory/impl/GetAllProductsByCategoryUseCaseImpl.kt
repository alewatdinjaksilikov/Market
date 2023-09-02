package com.example.market.domain.usecase.product.getAllProductsByCategory.impl

import com.example.market.data.models.ProductResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.main.MainRepository
import com.example.market.domain.usecase.product.getAllProductsByCategory.GetAllProductsByCategoryUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductsByCategoryUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    GetAllProductsByCategoryUseCase {
    override fun execute(id: Int): Flow<ResultData<List<ProductResponseData>>>
        = mainRepository.getAllProductByCategory(id = id)
}