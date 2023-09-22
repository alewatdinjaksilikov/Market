package com.bizlergroup.stockcontrol.presentation.ui.dialogs.addCategory.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bizlergroup.stockcontrol.data.models.AddCategoryRequestData
import com.bizlergroup.stockcontrol.data.models.AddCategoryResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.domain.usecase.category.addCategory.AddCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class AddCategoryDialogViewModel @Inject constructor(
    private val addCategoryUseCase: AddCategoryUseCase
):ViewModel() {

    private val _addedCategoryFlow = MutableSharedFlow<AddCategoryResponseData>()
    val addedCategoryFlow: SharedFlow<AddCategoryResponseData> get() = _addedCategoryFlow

    private val _messageCategoryFlow = MutableSharedFlow<String>()
    val messageCategoryFlow: SharedFlow<String> get() = _messageCategoryFlow

    private val _errorCategoryFlow = MutableSharedFlow<Throwable>()
    val errorCategoryFlow : SharedFlow<Throwable> get() = _errorCategoryFlow

    fun addCategory(body:AddCategoryRequestData){
        addCategoryUseCase.execute(body).onEach {
            when(it){
                is ResultData.Success->{
                    _addedCategoryFlow.emit(it.data)
                }
                is ResultData.Message->{
                    _messageCategoryFlow.emit(it.message)
                }
                is ResultData.Error->{
                    _errorCategoryFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}