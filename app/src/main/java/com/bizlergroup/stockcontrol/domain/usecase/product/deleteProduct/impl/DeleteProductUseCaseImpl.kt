package com.bizlergroup.stockcontrol.domain.usecase.product.deleteProduct.impl

import com.bizlergroup.stockcontrol.data.models.DeleteProductResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.domain.repository.main.MainRepository
import com.bizlergroup.stockcontrol.domain.usecase.product.deleteProduct.DeleteProductUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteProductUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    DeleteProductUseCase {
    override fun execute(id: Int): Flow<ResultData<DeleteProductResponseData>>
        = mainRepository.deleteProduct(productId = id)

}