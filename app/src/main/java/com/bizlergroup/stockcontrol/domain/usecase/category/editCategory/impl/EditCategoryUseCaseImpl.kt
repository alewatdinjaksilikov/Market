package com.bizlergroup.stockcontrol.domain.usecase.category.editCategory.impl

import com.bizlergroup.stockcontrol.data.models.AddCategoryRequestData
import com.bizlergroup.stockcontrol.data.models.EditProductResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.domain.repository.main.MainRepository
import com.bizlergroup.stockcontrol.domain.usecase.category.editCategory.EditCategoryUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EditCategoryUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    EditCategoryUseCase {
    override fun execute(body: AddCategoryRequestData, id: Int): Flow<ResultData<EditProductResponseData>>
        = mainRepository.editCategory(body = body, id = id)
}