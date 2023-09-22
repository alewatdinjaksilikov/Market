package com.bizlergroup.stockcontrol.domain.usecase.category.getCategoryById

import com.bizlergroup.stockcontrol.data.models.GetCategory
import com.bizlergroup.stockcontrol.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface GetCategoryByIdUseCase {
    fun execute(id:Int):Flow<ResultData<GetCategory>>
}