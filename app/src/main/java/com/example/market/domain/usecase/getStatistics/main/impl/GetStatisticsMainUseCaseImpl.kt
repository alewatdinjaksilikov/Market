package com.example.market.domain.usecase.getStatistics.main.impl

import com.example.market.data.models.ResultData
import com.example.market.data.models.StatisticsMainResponseData
import com.example.market.domain.repository.main.MainRepository
import com.example.market.domain.usecase.getStatistics.main.GetStatisticsMainUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStatisticsMainUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    GetStatisticsMainUseCase {
    override fun execute(): Flow<ResultData<StatisticsMainResponseData>>
        = mainRepository.getStatisticsMain()
}