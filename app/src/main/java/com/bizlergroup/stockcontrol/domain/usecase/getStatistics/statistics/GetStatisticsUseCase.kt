package com.bizlergroup.stockcontrol.domain.usecase.getStatistics.statistics

import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.data.models.StatisticsResponseData
import kotlinx.coroutines.flow.Flow


interface GetStatisticsUseCase {
    fun execute():Flow<ResultData<StatisticsResponseData>>
}