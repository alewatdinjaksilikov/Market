package com.bizlergroup.stockcontrol.domain.usecase.image.addImage.impl

import com.bizlergroup.stockcontrol.data.models.EditProductResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.domain.repository.main.MainRepository
import com.bizlergroup.stockcontrol.domain.usecase.image.addImage.AddImageUseCase
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import javax.inject.Inject

class AddImageUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    AddImageUseCase {
    override fun execute(body: MultipartBody.Part): Flow<ResultData<EditProductResponseData>>
        = mainRepository.addImage(body = body)
}