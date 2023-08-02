package com.example.market.domain.usecase.editCategory.impl

import com.example.market.data.models.AddCategoryRequestData
import com.example.market.data.models.EditProductResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.main.MainRepository
import com.example.market.domain.usecase.editCategory.EditCategoryUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EditCategoryUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    EditCategoryUseCase {
    override fun execute(body: AddCategoryRequestData, id: Int): Flow<ResultData<EditProductResponseData>> {
        return mainRepository.editCategory(body = body, id = id)
    }
}