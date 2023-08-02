package com.example.market.domain.usecase.addCategory.impl

import com.example.market.data.models.AddCategoryRequestData
import com.example.market.data.models.AddCategoryResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.main.MainRepository
import com.example.market.domain.usecase.addCategory.AddCategoryUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddCategoryUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    AddCategoryUseCase {
    override fun execute(body: AddCategoryRequestData): Flow<ResultData<AddCategoryResponseData>>{
        return mainRepository.addCategory(body = body)
    }
}