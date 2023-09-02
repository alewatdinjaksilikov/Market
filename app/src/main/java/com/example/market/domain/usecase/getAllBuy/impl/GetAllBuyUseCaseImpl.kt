package com.example.market.domain.usecase.getAllBuy.impl

import com.example.market.data.models.MonitoringResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.main.MainRepository
import com.example.market.domain.usecase.getAllBuy.GetAllBuyUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllBuyUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    GetAllBuyUseCase {
    override fun execute(): Flow<ResultData<List<MonitoringResponseData>>>
        = mainRepository.getAllBuy()
}