package com.example.market.domain.usecase.uploadStatistics.impl

import com.example.market.data.models.ResultData
import com.example.market.data.models.UploadStatisticsData
import com.example.market.domain.repository.main.MainRepository
import com.example.market.domain.usecase.uploadStatistics.UploadStatisticsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UploadStatisticsUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):UploadStatisticsUseCase {
    override fun execute(): Flow<ResultData<List<Byte>?>>  = mainRepository.uploadStatistics()
}