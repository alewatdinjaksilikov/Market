package com.example.market.domain.usecase.category.deleteCategory.impl

import com.example.market.data.models.EditProductResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.main.MainRepository
import com.example.market.domain.usecase.category.deleteCategory.DeleteCategoryUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteCategoryUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    DeleteCategoryUseCase {
    override fun execute(id: Int): Flow<ResultData<EditProductResponseData>> {
        return mainRepository.deleteCategory(id = id)
    }
}