package com.bizlergroup.stockcontrol.presentation.ui.image.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bizlergroup.stockcontrol.data.models.EditProductResponseData
import com.bizlergroup.stockcontrol.data.models.ImageResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.domain.usecase.image.addImage.AddImageUseCase
import com.bizlergroup.stockcontrol.domain.usecase.image.getAllImages.GetAllImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class ImagesFragmentViewModel @Inject constructor(
    private val getAllImagesUseCase: GetAllImagesUseCase,
    private val addImageUseCase: AddImageUseCase
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

    private val _addImage = MutableSharedFlow<EditProductResponseData>()
    val addImage: SharedFlow<EditProductResponseData> get() = _addImage

    private val _messageAddImage = MutableSharedFlow<String>()
    val messageAddImage: SharedFlow<String> get() = _messageAddImage

    private val _errorAddImage = MutableSharedFlow<Throwable>()
    val errorAddImage: SharedFlow<Throwable> get() = _errorAddImage

    fun addImage(body: MultipartBody.Part){
        addImageUseCase.execute(body).onEach {
            when(it){
                is ResultData.Success->{
                    _addImage.emit(it.data)
                }
                is ResultData.Message->{
                    _messageAddImage.emit(it.message)
                }
                is ResultData.Error->{
                    _errorAddImage.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }


}