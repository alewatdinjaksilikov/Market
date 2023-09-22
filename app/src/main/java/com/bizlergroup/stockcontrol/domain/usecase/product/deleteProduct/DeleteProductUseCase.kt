package com.bizlergroup.stockcontrol.domain.usecase.product.deleteProduct

import com.bizlergroup.stockcontrol.data.models.DeleteProductResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface DeleteProductUseCase {
    fun execute(id:Int):Flow<ResultData<DeleteProductResponseData>>
}