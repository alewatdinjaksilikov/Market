package com.example.market.domain.usecase.product.addAmount.impl

import com.example.market.data.models.AddAmountRequestData
import com.example.market.data.models.EditProductResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.main.MainRepository
import com.example.market.domain.usecase.product.addAmount.AddAmountProductUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddAmountProductUseCaseImpl @Inject constructor(
    private val mainRepository: MainRepository
):AddAmountProductUseCase {
    override fun execute(body: AddAmountRequestData): Flow<ResultData<EditProductResponseData?>>
        = mainRepository.addAmountProduct(body = body)
}