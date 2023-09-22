package com.bizlergroup.stockcontrol.presentation.ui.statistic.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.data.models.StatisticsResponseData
import com.bizlergroup.stockcontrol.domain.usecase.getStatistics.statistics.GetStatisticsUseCase
import com.bizlergroup.stockcontrol.domain.usecase.uploadStatistics.UploadStatisticsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StatisticsFragmentViewModel @Inject constructor(
    private val getStatisticsUseCase: GetStatisticsUseCase,
    private val uploadStatisticsUseCase: UploadStatisticsUseCase
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

    private val _uploadStatistics = MutableSharedFlow<List<Byte>?>()
    val uploadStatistics: SharedFlow<List<Byte>?> get() = _uploadStatistics

    private val _messageUpload = MutableSharedFlow<String>()
    val messageUpload: SharedFlow<String> get() = _messageUpload

    private val _errorUpload = MutableSharedFlow<Throwable>()
    val errorUpload: SharedFlow<Throwable> get() = _errorUpload

    fun uploadStatistics(){
        uploadStatisticsUseCase.execute().onEach {
            when(it){
                is ResultData.Success->{
                    _uploadStatistics.emit(it.data)
                }
                is ResultData.Message->{
                    _messageUpload.emit(it.message)
                }
                is ResultData.Error->{
                    _errorUpload.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}