package com.example.market.domain.usecase.impl

import com.example.market.data.models.AddCategoryRequestData
import com.example.market.data.models.AddCategoryResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.MainRepository
import com.example.market.domain.usecase.AddCategoryUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject
import kotlin.math.floor

class AddCategoryUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):AddCategoryUseCase{
    override fun execute(body: AddCategoryRequestData): Flow<ResultData<AddCategoryResponseData>>{
        return mainRepository.addCategory(body = body)
    }
}