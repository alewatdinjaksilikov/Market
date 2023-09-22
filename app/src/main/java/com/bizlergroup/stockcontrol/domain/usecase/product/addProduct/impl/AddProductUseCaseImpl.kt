package com.bizlergroup.stockcontrol.domain.usecase.product.addProduct.impl

import com.bizlergroup.stockcontrol.data.models.AddProductRequestData
import com.bizlergroup.stockcontrol.data.models.AddProductResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.domain.repository.main.MainRepository
import com.bizlergroup.stockcontrol.domain.usecase.product.addProduct.AddProductUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddProductUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    AddProductUseCase {

    override fun execute(body: AddProductRequestData): Flow<ResultData<AddProductResponseData>>
        = mainRepository.addProduct(body = body)
}