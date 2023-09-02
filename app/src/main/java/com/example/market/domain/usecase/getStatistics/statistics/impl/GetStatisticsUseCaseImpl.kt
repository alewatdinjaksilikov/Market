package com.example.market.domain.usecase.getStatistics.statistics.impl

import com.example.market.data.models.ResultData
import com.example.market.data.models.StatisticsMainResponseData
import com.example.market.data.models.StatisticsResponseData
import com.example.market.domain.repository.main.MainRepository
import com.example.market.domain.usecase.getStatistics.main.GetStatisticsMainUseCase
import com.example.market.domain.usecase.getStatistics.statistics.GetStatisticsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStatisticsUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    GetStatisticsUseCase {
    override fun execute(): Flow<ResultData<StatisticsResponseData>>
        = mainRepository.getStatistics()
}