package com.example.market.presentation.ui.statistic.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.market.data.models.ResultData
import com.example.market.data.models.StatisticsResponseData
import com.example.market.domain.usecase.getStatistics.statistics.GetStatisticsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StatisticsFragmentViewModel @Inject constructor(
    private val getStatisticsUseCase: GetStatisticsUseCase
):ViewModel() {

    private val _getStatisticsFlow = MutableSharedFlow<StatisticsResponseData>()
    val getStatisticsFlow: SharedFlow<StatisticsResponseData> get() = _getStatisticsFlow

    private val _messageGetStatisticsFlow = MutableSharedFlow<String>()
    val messageGetStatisticsFlow: SharedFlow<String> get() = _messageGetStatisticsFlow

    private val _errorGetStatisticsFlow = MutableSharedFlow<Throwable>()
    val errorGetStatisticsFlow: SharedFlow<Throwable> get() = _errorGetStatisticsFlow

    fun getStatistics(){
        getStatisticsUseCase.execute().onEach {
            when(it){
                is ResultData.Success->{
                    _getStatisticsFlow.emit(it.data)
                }
                is ResultData.Message->{
                    _messageGetStatisticsFlow.emit(it.message)
                }
                is ResultData.Error->{
                    _errorGetStatisticsFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}