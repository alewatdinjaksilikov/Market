package com.example.market.domain.usecase.getAllSale.impl

import com.example.market.data.models.MonitoringResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.main.MainRepository
import com.example.market.domain.usecase.getAllSale.GetAllSaleUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSaleUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    GetAllSaleUseCase {
    override fun execute(): Flow<ResultData<List<MonitoringResponseData>>> {
        return mainRepository.getAllSale()
    }
}