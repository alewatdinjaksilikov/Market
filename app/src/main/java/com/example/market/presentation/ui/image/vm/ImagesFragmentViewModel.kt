package com.example.market.presentation.ui.image.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.market.data.models.ImageResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.usecase.GetAllImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ImagesFragmentViewModel @Inject constructor(
    private val getAllImagesUseCase: GetAllImagesUseCase
):ViewModel() {

    private val _getAllImagesFlow = MutableSharedFlow<List<ImageResponseData>>()
    val getAllImagesFlow: SharedFlow<List<ImageResponseData>> get() = _getAllImagesFlow

    private val _messageGetAllImagesFlow = MutableSharedFlow<String>()
    val messageGetAllImagesFlow: SharedFlow<String> get() = _messageGetAllImagesFlow

    private val _errorGetAllImagesFlow = MutableSharedFlow<Throwable>()
    val errorGetAllImagesFlow: SharedFlow<Throwable> get() = _errorGetAllImagesFlow


    fun getAllImages(){
        getAllImagesUseCase.execute().onEach {
            when(it){
                is ResultData.Success->{
                    _getAllImagesFlow.emit(it.data)
                }
                is ResultData.Error ->{
                    _errorGetAllImagesFlow.emit(it.error)
                }
                is ResultData.Message->{
                    _messageGetAllImagesFlow.emit(it.message)
                }
            }
        }.launchIn(viewModelScope)
    }


}