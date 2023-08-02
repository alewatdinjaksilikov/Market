package com.example.market.domain.usecase.getAllSale

import com.example.market.data.models.MonitoringResponseData
import com.example.market.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface GetAllSaleUseCase {
    fun execute(): Flow<ResultData<List<MonitoringResponseData>>>
}