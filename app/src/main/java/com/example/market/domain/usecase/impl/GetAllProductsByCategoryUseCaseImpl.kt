package com.example.market.domain.usecase.impl

import com.example.market.data.models.Product
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.MainRepository
import com.example.market.domain.usecase.GetAllProductsByCategoryUseCase
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class GetAllProductsByCategoryUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :GetAllProductsByCategoryUseCase{
    override suspend fun execute(id: Int): Flow<ResultData<List<Product>>> {
        return mainRepository.getAllProductByCategory(id = id)
    }
}