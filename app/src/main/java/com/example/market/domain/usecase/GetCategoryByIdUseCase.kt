package com.example.market.domain.usecase

import com.example.market.data.models.GetCategory
import com.example.market.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface GetCategoryByIdUseCase {
    fun execute(id:Int):Flow<ResultData<GetCategory>>
}