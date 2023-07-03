package com.example.market.domain.usecase.impl

import com.example.market.data.models.ImageResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.MainRepository
import com.example.market.domain.usecase.GetAllImagesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllImagesUseCaseImpl @Inject constructor(
    private val mainRepository: MainRepository
):GetAllImagesUseCase {

    override fun execute(): Flow<ResultData<List<ImageResponseData>>> {
        return mainRepository.getAllImages()
    }
}