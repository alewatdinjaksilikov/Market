package com.example.market.domain.usecase

import com.example.market.data.models.CategoryResponseData
import com.example.market.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface GetAllCategoriesUseCase{
    fun execute():Flow<ResultData<List<CategoryResponseData>>>
}