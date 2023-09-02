package com.example.market.domain.usecase.category.getAllCategorires.impl

import com.example.market.data.models.CategoryResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.main.MainRepository
import com.example.market.domain.usecase.category.getAllCategorires.GetAllCategoriesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCategoriesUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    GetAllCategoriesUseCase {
    override fun execute(): Flow<ResultData<List<CategoryResponseData>>>
        = mainRepository.getAllCategories()

}