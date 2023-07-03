package com.example.market.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.market.data.models.AddCategoryRequestData
import com.example.market.data.models.AddCategoryResponseData
import com.example.market.data.models.CategoryResponseData
import com.example.market.domain.usecase.GetAllCategoriesUseCase
import com.example.market.data.models.ResultData
import com.example.market.domain.usecase.AddCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val addCategoryUseCase: AddCategoryUseCase
    ):ViewModel() {

    //GetAllCategories
    private val _getAllCategoriesFlow = MutableSharedFlow<List<CategoryResponseData>>()
    val getAllCategoriesFlow:SharedFlow<List<CategoryResponseData>> get() = _getAllCategoriesFlow

    private val _messageGetAllCategories = MutableSharedFlow<String>()
    val messageGetAllCategoriesFlow:SharedFlow<String> get() = _messageGetAllCategories

    private val _errorGetAllCategories = MutableSharedFlow<Throwable>()
    val errorGetAllCategoriesFlow:SharedFlow<Throwable> get() = _errorGetAllCategories

    //Add Category
    private val _addCategoryFlow = MutableSharedFlow<AddCategoryResponseData>()
    val addCategoryFlow:SharedFlow<AddCategoryResponseData> get() = _addCategoryFlow

    private val _messageAddCategoryFlow = MutableSharedFlow<String>()
    val messageAddCategoryFlow:SharedFlow<String> get() = _messageAddCategoryFlow

    private val _errorAddCategoryFlow = MutableSharedFlow<Throwable>()
    val errorAddCategoryFlow:SharedFlow<Throwable> get() = _errorAddCategoryFlow

    fun getAllCategories(){
        getAllCategoriesUseCase.execute().onEach {
            when(it){
                is ResultData.Success ->{
                    _getAllCategoriesFlow.emit(it.data)
                }
                is ResultData.Message ->{
                    _messageGetAllCategories.emit(it.message)
                }
                is ResultData.Error ->{
                    _errorGetAllCategories.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addCategory(body:AddCategoryRequestData){
        addCategoryUseCase.execute(body).onEach {
            when(it){
                is ResultData.Success ->{
                    _addCategoryFlow.emit(it.data)
                }
                is ResultData.Message ->{
                    _messageAddCategoryFlow.emit(it.message)
                }
                is ResultData.Error ->{
                    _errorAddCategoryFlow.emit(it.error)
                }
            }

        }.launchIn(viewModelScope)
    }
}