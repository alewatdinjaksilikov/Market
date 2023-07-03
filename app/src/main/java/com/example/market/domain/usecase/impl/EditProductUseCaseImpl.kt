package com.example.market.domain.usecase.impl

import com.example.market.data.models.EditProductRequestData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.MainRepository
import com.example.market.domain.usecase.EditProductUseCase
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class EditProductUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):EditProductUseCase {
    override fun execute(body: EditProductRequestData, id: Int): Flow<ResultData<Any>> {
        return mainRepository.editProductById(body = body,id = id)
    }
}