package com.bizlergroup.stockcontrol.domain.usecase.product.sellProduct.impl

import com.bizlergroup.stockcontrol.data.models.EditProductResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.data.models.SellProductRequestData
import com.bizlergroup.stockcontrol.domain.repository.main.MainRepository
import com.bizlergroup.stockcontrol.domain.usecase.product.sellProduct.SellProductUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SellProductUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    SellProductUseCase {
    override fun execute(body: SellProductRequestData): Flow<ResultData<EditProductResponseData>>
        = mainRepository.sellProduct(body = body)
}