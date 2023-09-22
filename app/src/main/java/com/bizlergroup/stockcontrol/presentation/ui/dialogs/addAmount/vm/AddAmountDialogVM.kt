package com.bizlergroup.stockcontrol.presentation.ui.dialogs.addAmount.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bizlergroup.stockcontrol.data.models.AddAmountRequestData
import com.bizlergroup.stockcontrol.data.models.EditProductResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.domain.usecase.product.addAmount.AddAmountProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AddAmountDialogVM @Inject constructor(
    private val addAmountProductUseCase: AddAmountProductUseCase
):ViewModel() {

    private val _addAmountFlow = MutableSharedFlow<EditProductResponseData?>()
    val addAmountFlow: SharedFlow<EditProductResponseData?> get() = _addAmountFlow

    private val _messageAddAmountFlow = MutableSharedFlow<String>()
    val messageAddAmountFlow: SharedFlow<String> get() = _messageAddAmountFlow

    private val _errorAddAmountFlow = MutableSharedFlow<Throwable>()
    val errorAddAmountFlow: SharedFlow<Throwable> get() = _errorAddAmountFlow

    fun addAmount(body : AddAmountRequestData){
        addAmountProductUseCase.execute(body = body).onEach {
            when(it){
                is ResultData.Success->{
                    _addAmountFlow.emit(it.data)
                }
                is ResultData.Message->{
                    _messageAddAmountFlow.emit(it.message)
                }
                is ResultData.Error->{
                    _errorAddAmountFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}