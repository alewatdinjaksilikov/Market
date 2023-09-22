package com.bizlergroup.stockcontrol.domain.usecase.uploadStatistics

import com.bizlergroup.stockcontrol.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface UploadStatisticsUseCase {
    fun execute():Flow<ResultData<List<Byte>?>>
}