package com.example.market.domain.usecase.image.getAllImages.impl

import com.example.market.data.models.ImageResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.main.MainRepository
import com.example.market.domain.usecase.image.getAllImages.GetAllImagesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllImagesUseCaseImpl @Inject constructor(
    private val mainRepository: MainRepository
): GetAllImagesUseCase {

    override fun execute(): Flow<ResultData<List<ImageResponseData>>>
        = mainRepository.getAllImages()
}