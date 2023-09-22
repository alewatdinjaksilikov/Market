package com.bizlergroup.stockcontrol.domain.usecase.getAllSale.impl

import com.bizlergroup.stockcontrol.data.models.MonitoringResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.domain.repository.main.MainRepository
import com.bizlergroup.stockcontrol.domain.usecase.getAllSale.GetAllSaleUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSaleUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    GetAllSaleUseCase {
    override fun execute(): Flow<ResultData<List<MonitoringResponseData>>>
        = mainRepository.getAllSale()
}