package com.example.market.domain.usecase.getStatistics.main

import com.example.market.data.models.ResultData
import com.example.market.data.models.StatisticsMainResponseData
import kotlinx.coroutines.flow.Flow

interface GetStatisticsMainUseCase {
    fun execute():Flow<ResultData<StatisticsMainResponseData>>
}