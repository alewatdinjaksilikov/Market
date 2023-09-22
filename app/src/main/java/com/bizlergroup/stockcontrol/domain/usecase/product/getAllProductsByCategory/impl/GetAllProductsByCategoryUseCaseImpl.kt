package com.bizlergroup.stockcontrol.domain.usecase.product.getAllProductsByCategory.impl

import com.bizlergroup.stockcontrol.data.models.ProductResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.domain.repository.main.MainRepository
import com.bizlergroup.stockcontrol.domain.usecase.product.getAllProductsByCategory.GetAllProductsByCategoryUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductsByCategoryUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    GetAllProductsByCategoryUseCase {
    override fun execute(id: Int): Flow<ResultData<List<ProductResponseData>>>
        = mainRepository.getAllProductByCategory(id = id)
}