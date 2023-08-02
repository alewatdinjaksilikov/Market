package com.example.market.domain.usecase.editProduct.impl

import com.example.market.data.models.EditProductRequestData
import com.example.market.data.models.EditProductResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.main.MainRepository
import com.example.market.domain.usecase.editProduct.EditProductUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EditProductUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    EditProductUseCase {
    override fun execute(body: EditProductRequestData, id: Int): Flow<ResultData<EditProductResponseData>> {
        return mainRepository.editProductById(body = body,id = id)
    }
}