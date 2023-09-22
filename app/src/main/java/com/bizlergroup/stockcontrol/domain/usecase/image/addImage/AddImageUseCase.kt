package com.bizlergroup.stockcontrol.domain.usecase.image.addImage

import com.bizlergroup.stockcontrol.data.models.EditProductResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface AddImageUseCase {

    fun execute(body: MultipartBody.Part): Flow<ResultData<EditProductResponseData>>
}