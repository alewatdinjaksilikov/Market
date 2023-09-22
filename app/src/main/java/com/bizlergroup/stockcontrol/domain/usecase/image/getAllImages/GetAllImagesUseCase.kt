package com.bizlergroup.stockcontrol.domain.usecase.image.getAllImages

import com.bizlergroup.stockcontrol.data.models.ImageResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface GetAllImagesUseCase {
    fun execute():Flow<ResultData<List<ImageResponseData>>>
}