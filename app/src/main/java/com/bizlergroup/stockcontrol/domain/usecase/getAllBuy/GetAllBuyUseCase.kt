package com.bizlergroup.stockcontrol.domain.usecase.getAllBuy

import com.bizlergroup.stockcontrol.data.models.MonitoringResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import kotlinx.coroutines.flow.Flow


interface GetAllBuyUseCase {
    fun execute(): Flow<ResultData<List<MonitoringResponseData>>>
}