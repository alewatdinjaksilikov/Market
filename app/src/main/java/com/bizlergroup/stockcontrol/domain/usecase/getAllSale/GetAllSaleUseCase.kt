package com.bizlergroup.stockcontrol.domain.usecase.getAllSale

import com.bizlergroup.stockcontrol.data.models.MonitoringResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface GetAllSaleUseCase {
    fun execute(): Flow<ResultData<List<MonitoringResponseData>>>
}