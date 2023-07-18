package com.example.market.domain.usecase.impl

import com.example.market.data.models.DeleteProductResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.MainRepository
import com.example.market.domain.usecase.DeleteProductUseCase
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class DeleteProductUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):DeleteProductUseCase {
    override fun execute(id: Int): Flow<ResultData<DeleteProductResponseData>> {
        return mainRepository.deleteProduct(productId = id)
    }

}