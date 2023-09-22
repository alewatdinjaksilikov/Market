package com.bizlergroup.stockcontrol.domain.usecase.image.getAllImages.impl

import com.bizlergroup.stockcontrol.data.models.ImageResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.domain.repository.main.MainRepository
import com.bizlergroup.stockcontrol.domain.usecase.image.getAllImages.GetAllImagesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllImagesUseCaseImpl @Inject constructor(
    private val mainRepository: MainRepository
): GetAllImagesUseCase {

    override fun execute(): Flow<ResultData<List<ImageResponseData>>>
        = mainRepository.getAllImages()
}