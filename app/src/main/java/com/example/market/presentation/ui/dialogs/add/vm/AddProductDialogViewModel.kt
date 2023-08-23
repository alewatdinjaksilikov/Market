package com.example.market.presentation.ui.dialogs.add.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.market.data.models.AddProductRequestData
import com.example.market.data.models.AddProductResponseData
import com.example.market.data.models.CategoryResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.usecase.product.addProduct.AddProductUseCase
import com.example.market.domain.usecase.category.getAllCategorires.GetAllCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AddProductDialogViewModel @Inject constructor(
    private val addProductUseCase: AddProductUseCase,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase
):ViewModel() {

    //Add product

    private var _addProductFlow = MutableSharedFlow<AddProductResponseData>()
    val addProductFlow:SharedFlow<AddProductResponseData> get() = _addProductFlow

    private var _mesageAddProductFlow = MutableSharedFlow<String>()
    val mesageAddProductFlow:SharedFlow<String> get() = _mesageAddProductFlow

    private var _errorAddProductFlow = MutableSharedFlow<Throwable>()
    val errorAddProductFlow:SharedFlow<Throwable> get() = _errorAddProductFlow


    fun addProduct(body:AddProductRequestData){
        addProductUseCase.execute(body = body).onEach {
            when(it){
                is ResultData.Success->{
                    _addProductFlow.emit(it.data)
                }
                is ResultData.Message->{
                    _mesageAddProductFlow.emit(it.message)
                }
                is ResultData.Error->{
                    _errorAddProductFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }


    //GetAllCategories

    private var _getCategoriesFlow = MutableSharedFlow<List<CategoryResponseData>>()
    val getCategoriesFlow:SharedFlow<List<CategoryResponseData>> get() = _getCategoriesFlow

    private var _messageGetCategoriesFlow = MutableSharedFlow<String>()
    val messageGetCategoriesFlow:SharedFlow<String> get() = _messageGetCategoriesFlow

    private var _errorGetCategoriesFlow = MutableSharedFlow<Throwable>()
    val errorGetCategoriesFlow:SharedFlow<Throwable> get() = _errorGetCategoriesFlow


    fun getAllCategories(){
        getAllCategoriesUseCase.execute().onEach {
            when(it){
                is ResultData.Success->{
                    _getCategoriesFlow.emit(it.data)
                }
                is ResultData.Message->{
                    _messageGetCategoriesFlow.emit(it.message)
                }
                is ResultData.Error->{
                    _errorGetCategoriesFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}