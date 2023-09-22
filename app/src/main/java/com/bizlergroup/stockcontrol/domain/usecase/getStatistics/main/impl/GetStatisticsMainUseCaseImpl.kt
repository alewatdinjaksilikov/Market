package com.bizlergroup.stockcontrol.domain.usecase.getStatistics.main.impl

import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.data.models.StatisticsMainResponseData
import com.bizlergroup.stockcontrol.domain.repository.main.MainRepository
import com.bizlergroup.stockcontrol.domain.usecase.getStatistics.main.GetStatisticsMainUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStatisticsMainUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    GetStatisticsMainUseCase {
    override fun execute(): Flow<ResultData<StatisticsMainResponseData>>
        = mainRepository.getStatisticsMain()
}