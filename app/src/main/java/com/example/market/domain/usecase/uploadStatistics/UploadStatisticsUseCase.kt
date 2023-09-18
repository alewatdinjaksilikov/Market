package com.example.market.domain.usecase.uploadStatistics

import com.example.market.data.models.ResultData
import com.example.market.data.models.UploadStatisticsData
import kotlinx.coroutines.flow.Flow

interface UploadStatisticsUseCase {
    fun execute():Flow<ResultData<List<Byte>?>>
}