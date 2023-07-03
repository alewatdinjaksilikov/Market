package com.example.market.presentation.vm


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.market.data.models.AddProductRequestData
import com.example.market.data.models.Product
import com.example.market.data.models.ResultData
import com.example.market.domain.usecase.AddProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val addProductUseCase: AddProductUseCase
): ViewModel() {

    private val _addProductLiveData = MutableLiveData<Any?>()
    val addProductLiveData:LiveData<Any?> get() = _addProductLiveData

    private val _messageAddProductMessageLiveData = MutableLiveData<String>()
    val messageAddProductMessageLiveData:LiveData<String> get() = _messageAddProductMessageLiveData

    private val _errorAddProductMessageLiveData = MutableLiveData<Throwable>()
    val errorAddProductMessageLiveData:LiveData<Throwable> get() = _errorAddProductMessageLiveData

    fun addProduct(body:AddProductRequestData){
        addProductUseCase.execute(body = body).onEach {
            when(it){
                is ResultData.Success ->{
                    _addProductLiveData.value = it.data
                }
                is ResultData.Message -> {
                    _messageAddProductMessageLiveData.value = it.message
                }
                is ResultData.Error ->{
                    _errorAddProductMessageLiveData.value = it.error
                }
            }
        }.launchIn(viewModelScope)
    }
}