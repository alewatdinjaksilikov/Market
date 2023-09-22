package com.bizlergroup.stockcontrol.domain.usecase.product.editProduct.impl

import com.bizlergroup.stockcontrol.data.models.EditProductRequestData
import com.bizlergroup.stockcontrol.data.models.EditProductResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.domain.repository.main.MainRepository
import com.bizlergroup.stockcontrol.domain.usecase.product.editProduct.EditProductUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EditProductUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    EditProductUseCase {
    override fun execute(body: EditProductRequestData, id: Int): Flow<ResultData<EditProductResponseData>>
        = mainRepository.editProductById(body = body,id = id)
}