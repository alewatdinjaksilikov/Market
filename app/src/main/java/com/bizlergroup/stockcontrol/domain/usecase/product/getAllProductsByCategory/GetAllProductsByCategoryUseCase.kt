package com.bizlergroup.stockcontrol.domain.usecase.product.getAllProductsByCategory

import com.bizlergroup.stockcontrol.data.models.ProductResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface GetAllProductsByCategoryUseCase{
    fun execute(id:Int): Flow<ResultData<List<ProductResponseData>>>
}