package com.bizlergroup.stockcontrol.domain.usecase.category.getCategoryById.impl

import com.bizlergroup.stockcontrol.data.models.GetCategory
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.domain.repository.main.MainRepository
import com.bizlergroup.stockcontrol.domain.usecase.category.getCategoryById.GetCategoryByIdUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryByIdUseCaseImpl @Inject constructor (private val mainRepository: MainRepository):
    GetCategoryByIdUseCase {
    override fun execute(id: Int): Flow<ResultData<GetCategory>>
        = mainRepository.getCategoryById(id = id)
}