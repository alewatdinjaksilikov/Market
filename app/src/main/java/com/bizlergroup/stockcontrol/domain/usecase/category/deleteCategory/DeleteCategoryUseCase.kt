package com.bizlergroup.stockcontrol.domain.usecase.category.deleteCategory

import com.bizlergroup.stockcontrol.data.models.EditProductResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface DeleteCategoryUseCase{

    fun execute(id:Int):Flow<ResultData<EditProductResponseData>>
}