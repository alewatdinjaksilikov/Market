package com.bizlergroup.stockcontrol.domain.usecase.getStatistics.main

import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.data.models.StatisticsMainResponseData
import kotlinx.coroutines.flow.Flow

interface GetStatisticsMainUseCase {
    fun execute():Flow<ResultData<StatisticsMainResponseData>>
}