package com.example.market.presentation.ui.dialogs.listProducts.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.market.data.models.ProductResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.usecase.GetAllProductsByCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ListProductsDialogViewModel @Inject constructor(
    private val getAllProductsByCategoryUseCase: GetAllProductsByCategoryUseCase
) :ViewModel() {

    private val _getAllProductsFlow = MutableSharedFlow<List<ProductResponseData>>()
    val getAllProductsFlow: SharedFlow<List<ProductResponseData>> get() = _getAllProductsFlow

    private val _messageGetAllProducts = MutableSharedFlow<String>()
    val messageGetAllProducts:SharedFlow<String> get() = _messageGetAllProducts

    private val _errorGetAllProducts = MutableSharedFlow<Throwable>()
    val errorGetAllProducts:SharedFlow<Throwable> get() = _errorGetAllProducts

    fun getAllProductByCategories(id:Int){
        getAllProductsByCategoryUseCase.execute(id =  id).onEach {
            when(it){
                is ResultData.Success->{
                    _getAllProductsFlow.emit(it.data)
                }
                is ResultData.Message ->{
                    _messageGetAllProducts.emit(it.message)
                }
                is ResultData.Error ->{
                    _errorGetAllProducts.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}