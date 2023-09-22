package com.bizlergroup.stockcontrol.domain.usecase.category.getAllCategorires

import com.bizlergroup.stockcontrol.data.models.CategoryResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface GetAllCategoriesUseCase{
    fun execute():Flow<ResultData<List<CategoryResponseData>>>
}