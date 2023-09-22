package com.bizlergroup.stockcontrol.domain.usecase.product.getAllProducts.impl

import com.bizlergroup.stockcontrol.data.models.ProductResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.domain.repository.main.MainRepository
import com.bizlergroup.stockcontrol.domain.usecase.product.getAllProducts.GetAllProductsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductsUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    GetAllProductsUseCase {
    override fun execute(): Flow<ResultData<List<ProductResponseData>>>
        = mainRepository.getAllProducts()
}