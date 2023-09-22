package com.bizlergroup.stockcontrol.domain.usecase.category.addCategory.impl

import com.bizlergroup.stockcontrol.data.models.AddCategoryRequestData
import com.bizlergroup.stockcontrol.data.models.AddCategoryResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.domain.repository.main.MainRepository
import com.bizlergroup.stockcontrol.domain.usecase.category.addCategory.AddCategoryUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddCategoryUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    AddCategoryUseCase {
    override fun execute(body: AddCategoryRequestData): Flow<ResultData<AddCategoryResponseData>>
        = mainRepository.addCategory(body = body)
}