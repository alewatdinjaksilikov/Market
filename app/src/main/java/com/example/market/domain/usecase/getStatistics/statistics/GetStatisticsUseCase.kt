package com.example.market.domain.usecase.getStatistics.statistics

import com.example.market.data.models.ResultData
import com.example.market.data.models.StatisticsResponseData
import kotlinx.coroutines.flow.Flow


interface GetStatisticsUseCase {
    fun execute():Flow<ResultData<StatisticsResponseData>>
}