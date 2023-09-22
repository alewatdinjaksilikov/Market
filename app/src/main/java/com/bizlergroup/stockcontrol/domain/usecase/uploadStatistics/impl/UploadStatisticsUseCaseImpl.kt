package com.bizlergroup.stockcontrol.domain.usecase.uploadStatistics.impl

import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.domain.repository.main.MainRepository
import com.bizlergroup.stockcontrol.domain.usecase.uploadStatistics.UploadStatisticsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UploadStatisticsUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):UploadStatisticsUseCase {
    override fun execute(): Flow<ResultData<List<Byte>?>>  = mainRepository.uploadStatistics()
}