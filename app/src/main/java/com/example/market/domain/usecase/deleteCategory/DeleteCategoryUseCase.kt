package com.example.market.domain.usecase.deleteCategory

import com.example.market.data.models.EditProductResponseData
import com.example.market.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface DeleteCategoryUseCase{

    fun execute(id:Int):Flow<ResultData<EditProductResponseData>>
}