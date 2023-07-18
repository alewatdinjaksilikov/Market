package com.example.market.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.market.data.models.EditProductRequestData
import com.example.market.data.models.EditProductResponseData
import com.example.market.data.models.ProductResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.usecase.EditProductUseCase
import com.example.market.domain.usecase.GetProductByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class EditProductDialogViewModel @Inject constructor(
    private var getProductByNameUseCase: GetProductByNameUseCase,
    private var editProductUseCase: EditProductUseCase
):ViewModel() {

    private val _getProductFlow = MutableSharedFlow<ProductResponseData>()
    val getProductFlow : SharedFlow<ProductResponseData> get() = _getProductFlow

    private val _messageGetProductFlow = MutableSharedFlow<String>()
    val messageGetProductFlow : SharedFlow<String> get() = _messageGetProductFlow

    private val _errorGetProductFlow = MutableSharedFlow<Throwable>()
    val errorGetProductFlow : SharedFlow<Throwable> get() = _errorGetProductFlow



    private val _editProductFlow = MutableSharedFlow<EditProductResponseData>()
    val editProductFlow : SharedFlow<EditProductResponseData> get() = _editProductFlow

    private val _messageEditProductFlow = MutableSharedFlow<String>()
    val messageEditProductFlow : SharedFlow<String> get() = _messageEditProductFlow

    private val _errorEditProductFlow = MutableSharedFlow<Throwable>()
    val errorEditProductFlow : SharedFlow<Throwable> get() = _errorEditProductFlow

    fun getProductByName(name: String){
        getProductByNameUseCase.execute(name = name).onEach {
            when(it){
                is ResultData.Success->{
                    _getProductFlow.emit(it.data)
                }
                is ResultData.Message-> {
                    _messageGetProductFlow.emit(it.message)
                }
                is ResultData.Error->{
                    _errorGetProductFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun editProduct(id:Int,body : EditProductRequestData){
        editProductUseCase.execute(body = body, id = id).onEach {
            when(it){
                is ResultData.Success->{
                    _editProductFlow.emit(it.data)
                }
                is ResultData.Message->{
                    _messageEditProductFlow.emit(it.message)
                }
                is ResultData.Error->{
                    _errorEditProductFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}