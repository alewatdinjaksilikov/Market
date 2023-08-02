package com.example.market.domain.usecase.editProduct

import com.example.market.data.models.EditProductRequestData
import com.example.market.data.models.EditProductResponseData
import com.example.market.data.models.ResultData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface EditProductUseCase {
    fun execute(body: EditProductRequestData,id:Int):Flow<ResultData<EditProductResponseData>>
}