package com.bizlergroup.stockcontrol.domain.usecase.product.addAmount.impl

import com.bizlergroup.stockcontrol.data.models.AddAmountRequestData
import com.bizlergroup.stockcontrol.data.models.EditProductResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.domain.repository.main.MainRepository
import com.bizlergroup.stockcontrol.domain.usecase.product.addAmount.AddAmountProductUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddAmountProductUseCaseImpl @Inject constructor(
    private val mainRepository: MainRepository
):AddAmountProductUseCase {
    override fun execute(body: AddAmountRequestData): Flow<ResultData<EditProductResponseData?>>
        = mainRepository.addAmountProduct(body = body)
}