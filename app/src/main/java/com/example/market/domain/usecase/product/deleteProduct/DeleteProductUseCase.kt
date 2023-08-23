package com.example.market.domain.usecase.product.deleteProduct

import com.example.market.data.models.DeleteProductResponseData
import com.example.market.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface DeleteProductUseCase {
    fun execute(id:Int):Flow<ResultData<DeleteProductResponseData>>
}