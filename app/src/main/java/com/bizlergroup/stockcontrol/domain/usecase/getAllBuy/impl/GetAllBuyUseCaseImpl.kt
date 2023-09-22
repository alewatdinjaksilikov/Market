package com.bizlergroup.stockcontrol.domain.usecase.getAllBuy.impl

import com.bizlergroup.stockcontrol.data.models.MonitoringResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.domain.repository.main.MainRepository
import com.bizlergroup.stockcontrol.domain.usecase.getAllBuy.GetAllBuyUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllBuyUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    GetAllBuyUseCase {
    override fun execute(): Flow<ResultData<List<MonitoringResponseData>>>
        = mainRepository.getAllBuy()
}