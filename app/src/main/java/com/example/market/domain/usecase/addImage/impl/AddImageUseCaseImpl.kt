package com.example.market.domain.usecase.addImage.impl

import com.example.market.data.models.EditProductResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.main.MainRepository
import com.example.market.domain.usecase.addImage.AddImageUseCase
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import javax.inject.Inject

class AddImageUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):AddImageUseCase {
    override fun execute(body: MultipartBody.Part): Flow<ResultData<EditProductResponseData>> {
        return mainRepository.addImage(body = body)
    }
}