package com.example.market.domain.usecase.impl

import com.example.market.data.models.GetCategory
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.MainRepository
import com.example.market.domain.usecase.GetCategoryByIdUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryByIdUseCaseImpl @Inject constructor (private val mainRepository: MainRepository):GetCategoryByIdUseCase {
    override fun execute(id: Int): Flow<ResultData<GetCategory>> {
        return mainRepository.getCategoryById(id = id)
    }
}