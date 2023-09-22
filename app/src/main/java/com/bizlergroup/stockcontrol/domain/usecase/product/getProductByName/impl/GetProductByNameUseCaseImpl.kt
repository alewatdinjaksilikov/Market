package com.bizlergroup.stockcontrol.domain.usecase.product.getProductByName.impl

import com.bizlergroup.stockcontrol.data.models.ProductResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.domain.repository.main.MainRepository
import com.bizlergroup.stockcontrol.domain.usecase.product.getProductByName.GetProductByNameUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductByNameUseCaseImpl @Inject constructor(
    private val mainRepository: MainRepository
): GetProductByNameUseCase {
    override fun execute(name: String): Flow<ResultData<ProductResponseData>>
        = mainRepository.getProductByName(name = name)
}