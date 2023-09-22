package com.bizlergroup.stockcontrol.domain.usecase.category.deleteCategory.impl

import com.bizlergroup.stockcontrol.data.models.EditProductResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.domain.repository.main.MainRepository
import com.bizlergroup.stockcontrol.domain.usecase.category.deleteCategory.DeleteCategoryUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteCategoryUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    DeleteCategoryUseCase {
    override fun execute(id: Int): Flow<ResultData<EditProductResponseData>>
        = mainRepository.deleteCategory(id = id)
}