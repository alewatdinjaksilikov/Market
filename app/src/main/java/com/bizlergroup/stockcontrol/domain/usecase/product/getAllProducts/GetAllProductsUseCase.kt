package com.bizlergroup.stockcontrol.domain.usecase.product.getAllProducts

import com.bizlergroup.stockcontrol.data.models.ProductResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface GetAllProductsUseCase {
    fun execute():Flow<ResultData<List<ProductResponseData>>>
}