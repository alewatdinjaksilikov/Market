package com.bizlergroup.stockcontrol.domain.usecase.product.editProduct

import com.bizlergroup.stockcontrol.data.models.EditProductRequestData
import com.bizlergroup.stockcontrol.data.models.EditProductResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface EditProductUseCase {
    fun execute(body: EditProductRequestData,id:Int):Flow<ResultData<EditProductResponseData>>
}