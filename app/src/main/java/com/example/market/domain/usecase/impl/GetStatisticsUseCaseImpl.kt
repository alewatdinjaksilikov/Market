package com.example.market.domain.usecase.impl

import com.example.market.data.models.ResultData
import com.example.market.data.models.StatisticsResponseData
import com.example.market.domain.repository.MainRepository
import com.example.market.domain.usecase.GetStatisticsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStatisticsUseCaseImpl @Inject constructor(private val mainRepository: MainRepository): GetStatisticsUseCase {
    override fun execute(): Flow<ResultData<StatisticsResponseData>> {
        return mainRepository.getStatistics()
    }
}