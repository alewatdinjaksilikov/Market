package com.example.market.presentation.ui.monitoring.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.market.data.models.MonitoringResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.usecase.getAllBuy.GetAllBuyUseCase
import com.example.market.domain.usecase.getAllSale.GetAllSaleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MonitoringFragmentViewModel @Inject constructor(
    private val getAllBuyUseCase: GetAllBuyUseCase,
    private val getAllSaleUseCase: GetAllSaleUseCase
):ViewModel() {

    private val _getAllBuyFlow = MutableSharedFlow<List<MonitoringResponseData>>()
    val getAllBuyFlow : SharedFlow<List<MonitoringResponseData>> get() = _getAllBuyFlow

    private val _messageBuyFlow = MutableSharedFlow<String>()
    val messageBuyFlow : SharedFlow<String> get() = _messageBuyFlow

    private val _errorBuyFlow = MutableSharedFlow<Throwable>()
    val errorBuyFlow : SharedFlow<Throwable> get() = _errorBuyFlow


    private val _getAllSaleFlow = MutableSharedFlow<List<MonitoringResponseData>>()
    val getAllSaleFlow : SharedFlow<List<MonitoringResponseData>> get() = _getAllSaleFlow

    private val _messageSaleFlow = MutableSharedFlow<String>()
    val messageSaleFlow : SharedFlow<String> get() = _messageSaleFlow

    private val _errorSaleFlow = MutableSharedFlow<Throwable>()
    val errorSaleFlow : SharedFlow<Throwable> get() = _errorSaleFlow


    fun getAllBuy(){
        getAllBuyUseCase.execute().onEach {
            when(it){
                is ResultData.Success ->{
                    _getAllBuyFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    _messageBuyFlow.emit(it.message)
                }
                is ResultData.Error ->{
                    _errorBuyFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getAllSale(){
        getAllSaleUseCase.execute().onEach {
            when(it){
                is ResultData.Success ->{
                    _getAllSaleFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    _messageSaleFlow.emit(it.message)
                }
                is ResultData.Error ->{
                    _errorSaleFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}