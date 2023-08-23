package com.example.market.presentation.ui.dialogs.listProducts.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.market.data.models.ProductResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.usecase.product.getAllProductsByCategory.GetAllProductsByCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ListProductsDialogViewModel @Inject constructor(
    private val getAllProductsByCategoryUseCase: GetAllProductsByCategoryUseCase
) :ViewModel() {

    private val _getAllProductsFlow = MutableSharedFlow<List<ProductResponseData>>()
    val getAllProductsFlow: SharedFlow<List<ProductResponseData>> get() = _getAllProductsFlow

    private val _messageGetAllProductsFlow = MutableSharedFlow<String>()
    val messageGetAllProductsFlow:SharedFlow<String> get() = _messageGetAllProductsFlow

    private val _errorGetAllProductsFlow = MutableSharedFlow<Throwable>()
    val errorGetAllProductsFlow:SharedFlow<Throwable> get() = _errorGetAllProductsFlow

    fun getAllProductByCategories(id:Int){
        getAllProductsByCategoryUseCase.execute(id =  id).onEach {
            when(it){
                is ResultData.Success->{
                    _getAllProductsFlow.emit(it.data)
                }
                is ResultData.Message ->{
                    _messageGetAllProductsFlow.emit(it.message)
                }
                is ResultData.Error ->{
                    _errorGetAllProductsFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}