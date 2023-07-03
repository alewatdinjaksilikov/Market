package com.example.market.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.market.data.models.AddCategoryRequestData
import com.example.market.data.models.CategoryResponseData
import com.example.market.data.models.EditProductRequestData
import com.example.market.data.models.ResultData
import com.example.market.domain.usecase.AddCategoryUseCase
import com.example.market.domain.usecase.EditProductUseCase
import com.example.market.domain.usecase.GetAllCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StockFragmentViewModel @Inject constructor(
    private val editProductUseCase: EditProductUseCase,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val addCategoryUseCase: AddCategoryUseCase
) : ViewModel() {

    private val _addCategoryLiveData = MutableLiveData<Any?>()
    val addCategoryLiveData:LiveData<Any?> get() = _addCategoryLiveData

    private val _messageAddCategoryLiveData = MutableLiveData<String>()
    val messageAddCategoryLiveData:LiveData<String> get() = _messageAddCategoryLiveData

    private val _errorAddCategoryLiveData = MutableLiveData<Throwable>()
    val errorAddCategoryLiveData:LiveData<Throwable> get() = _errorAddCategoryLiveData

    private val _editProductLiveData = MutableLiveData<Any?>()
    val editProductLiveData:LiveData<Any?> get() = _editProductLiveData

    private val _messageEditProductLiveData = MutableLiveData<String>()
    val messageEditProductLiveData:LiveData<String> get() = _messageEditProductLiveData

    private val _errorEditProductLiveData = MutableLiveData<Throwable>()
    val errorEditProductLiveData:LiveData<Throwable> get() = _errorEditProductLiveData

    private val _getAllCategoriesLiveData = MutableLiveData<List<CategoryResponseData>?>()
    val getAllCategoriesLiveData:LiveData<List<CategoryResponseData>?> get() = _getAllCategoriesLiveData

    private val _messageGetAllCategoriesLiveData = MutableLiveData<String>()
    val messageGetAllCategoriesLiveData:LiveData<String> get() = _messageGetAllCategoriesLiveData

    private val _errorGetAllCategoriesLiveData = MutableLiveData<Throwable>()
    val errorGetAllCategoriesLiveData:LiveData<Throwable> get() = _errorGetAllCategoriesLiveData

    fun addCategory(body: AddCategoryRequestData){
        addCategoryUseCase.execute(body = body).onEach {
            when(it){
                is ResultData.Success ->{
                    _addCategoryLiveData.value = it.data
                }
                is ResultData.Message ->{
                    _messageAddCategoryLiveData.value = it.message
                }
                is ResultData.Error ->{
                    _errorAddCategoryLiveData.value = it.error
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getAllCategories(){
        getAllCategoriesUseCase.execute().onEach {
            when(it){
                is ResultData.Success ->{
                    _getAllCategoriesLiveData.value = it.data
                }
                is ResultData.Message ->{
                    _messageGetAllCategoriesLiveData.value = it.message
                }
                is ResultData.Error ->{
                    _errorGetAllCategoriesLiveData.value = it.error
                }
            }
        }.launchIn(viewModelScope)
    }

    fun editProduct(body: EditProductRequestData,id:Int){
        editProductUseCase.execute(body = body,id = id).onEach {
            when(it){
                is ResultData.Success->{
                    _editProductLiveData.value = it.data
                }
                is ResultData.Message->{
                    _messageEditProductLiveData.value = it.message
                }
                is ResultData.Error->{
                    _errorEditProductLiveData.value = it.error
                }
            }
        }.launchIn(viewModelScope)
    }
}