package com.example.market.domain.usecase.impl

import com.example.market.data.models.AddCategoryRequestData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.MainRepository
import com.example.market.domain.usecase.EditCategoryUseCase
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class EditCategoryUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):EditCategoryUseCase {
    override suspend fun execute(body: AddCategoryRequestData, id: Int): Flow<ResultData<Any>> {
        return mainRepository.editCategory(body = body, id = id)
    }
}