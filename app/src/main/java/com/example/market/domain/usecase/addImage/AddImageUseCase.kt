package com.example.market.domain.usecase.addImage

import com.example.market.data.models.EditProductResponseData
import com.example.market.data.models.ResultData
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface AddImageUseCase {

    fun execute(body: MultipartBody.Part): Flow<ResultData<EditProductResponseData>>
}