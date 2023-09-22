package com.bizlergroup.stockcontrol.presentation.ui.dialogs.editCategory.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bizlergroup.stockcontrol.data.models.AddCategoryRequestData
import com.bizlergroup.stockcontrol.data.models.EditProductResponseData
import com.bizlergroup.stockcontrol.data.models.GetCategory
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.domain.usecase.category.editCategory.EditCategoryUseCase
import com.bizlergroup.stockcontrol.domain.usecase.category.getCategoryById.GetCategoryByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class EditCategoryViewModel @Inject constructor(
    private val editCategoryUseCase: EditCategoryUseCase,
    private val getCategoryByIdUseCase: GetCategoryByIdUseCase
):ViewModel() {

    //Edit Category

    private val _editCategoryFlow = MutableSharedFlow<EditProductResponseData>()
    val editCategoryFlow : SharedFlow<EditProductResponseData> get() = _editCategoryFlow

    private val _messageEditCategoryFlow = MutableSharedFlow<String>()
    val messageEditCategoryFlow : SharedFlow<String> get() = _messageEditCategoryFlow

    private val _errorEditCategoryFlow = MutableSharedFlow<Throwable>()
    val errorEditCategoryFlow : SharedFlow<Throwable> get() = _errorEditCategoryFlow

    fun editCategory(body: AddCategoryRequestData,id:Int){
        editCategoryUseCase.execute(body, id).onEach {
            when(it){
                is ResultData.Success->{
                    _editCategoryFlow.emit(it.data)
                }
                is ResultData.Message->{
                    _messageEditCategoryFlow.emit(it.message)
                }
                is ResultData.Error->{
                    _errorEditCategoryFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    //Get Category by Id

    private val _getCategoryFLow = MutableSharedFlow<GetCategory>()
    val getCategoryFLow : SharedFlow<GetCategory> get() = _getCategoryFLow

    private val _messageGetCategoryFlow = MutableSharedFlow<String>()
    val messageGetCategoryFlow : SharedFlow<String> get() = _messageGetCategoryFlow

    private val _errorGetCategoryFlow = MutableSharedFlow<Throwable>()
    val errorGetCategoryFlow : SharedFlow<Throwable> get() = _errorGetCategoryFlow

    fun getCategoryById(id:Int){
        getCategoryByIdUseCase.execute(id).onEach {
            when(it){
                is ResultData.Success->{
                    _getCategoryFLow.emit(it.data)
                }
                is ResultData.Message->{
                    _messageGetCategoryFlow.emit(it.message)
                }
                is ResultData.Error->{
                    _errorGetCategoryFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}