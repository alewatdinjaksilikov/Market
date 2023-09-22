package com.bizlergroup.stockcontrol.domain.usecase.getStatistics.statistics.impl

import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.data.models.StatisticsResponseData
import com.bizlergroup.stockcontrol.domain.repository.main.MainRepository
import com.bizlergroup.stockcontrol.domain.usecase.getStatistics.statistics.GetStatisticsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStatisticsUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    GetStatisticsUseCase {
    override fun execute(): Flow<ResultData<StatisticsResponseData>>
        = mainRepository.getStatistics()
}