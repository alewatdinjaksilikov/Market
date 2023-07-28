package com.example.market.domain.usecase

import com.example.market.data.models.EditProductResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface DeleteCategoryUseCase{

    fun execute(id:Int):Flow<ResultData<EditProductResponseData>>
}