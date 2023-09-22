package com.bizlergroup.stockcontrol.domain.usecase.category.getAllCategorires.impl

import com.bizlergroup.stockcontrol.data.models.CategoryResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.domain.repository.main.MainRepository
import com.bizlergroup.stockcontrol.domain.usecase.category.getAllCategorires.GetAllCategoriesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCategoriesUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    GetAllCategoriesUseCase {
    override fun execute(): Flow<ResultData<List<CategoryResponseData>>>
        = mainRepository.getAllCategories()

}