package com.bizlergroup.stockcontrol.domain.usecase.product.sellProduct

import com.bizlergroup.stockcontrol.data.models.EditProductResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.data.models.SellProductRequestData
import kotlinx.coroutines.flow.Flow

interface SellProductUseCase {
    fun execute(body:SellProductRequestData):Flow<ResultData<EditProductResponseData>>
}