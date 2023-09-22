package com.bizlergroup.stockcontrol.domain.usecase.product.getProductByName

import com.bizlergroup.stockcontrol.data.models.ProductResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface GetProductByNameUseCase {
    fun execute(name:String): Flow<ResultData<ProductResponseData>>
}