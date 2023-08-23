package com.example.market.domain.usecase.image.getAllImages

import com.example.market.data.models.ImageResponseData
import com.example.market.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface GetAllImagesUseCase {
    fun execute():Flow<ResultData<List<ImageResponseData>>>
}