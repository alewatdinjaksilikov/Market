package com.bizlergroup.stockcontrol.presentation.ui.stock.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bizlergroup.stockcontrol.data.models.*
import com.bizlergroup.stockcontrol.domain.usecase.category.deleteCategory.DeleteCategoryUseCase
import com.bizlergroup.stockcontrol.domain.usecase.product.deleteProduct.DeleteProductUseCase
import com.bizlergroup.stockcontrol.domain.usecase.category.getAllCategorires.GetAllCategoriesUseCase
import com.bizlergroup.stockcontrol.domain.usecase.product.getAllProductsByCategory.GetAllProductsByCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StockFragmentViewModel @Inject constructor(
    private val deleteProductUseCase: DeleteProductUseCase,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val getAllProductsByCategoryUseCase: GetAllProductsByCategoryUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase
) : ViewModel() {


    private val _deleteCategoryFlow = MutableSharedFlow<EditProductResponseData>()
    val deleteCategoryFlow : SharedFlow<EditProductResponseData> get() = _deleteCategoryFlow

    private val _messageDeleteCategory = MutableSharedFlow<String>()
    val messageDeleteCategory: SharedFlow<String> get() = _messageDeleteCategory

    private val _errorDeleteCategory = MutableSharedFlow<Throwable>()
    val errorDeleteCategory : SharedFlow<Throwable> get() = _errorDeleteCategory



    private val _getAllProduct = MutableSharedFlow<List<ProductResponseData>>()
    val getAllProduct : SharedFlow<List<ProductResponseData>> get() = _getAllProduct

    private val _messageGetAllProduct = MutableSharedFlow<String>()
    val messageGetAllProduct : SharedFlow<String> get() = _messageGetAllProduct

    private val _errorGetAllProduct = MutableSharedFlow<Throwable>()
    val errorGetAllProduct : SharedFlow<Throwable> get() = _errorGetAllProduct



    private val _getAllCategoriesFlow = MutableSharedFlow<List<CategoryResponseData>>()
    val getAllCategoriesFlow:SharedFlow<List<CategoryResponseData>> get() = _getAllCategoriesFlow

    private val _messageGetAllCategoriesFlow = MutableSharedFlow<String>()
    val messageGetAllCategoriesFlow:SharedFlow<String> get() = _messageGetAllCategoriesFlow

    private val _errorGetAllCategoriesFlow = MutableSharedFlow<Throwable>()
    val errorGetAllCategoriesFlow:SharedFlow<Throwable> get() = _errorGetAllCategoriesFlow


    private val _deleteProductFlow = MutableSharedFlow<DeleteProductResponseData>()
    val deleteProductFlow:SharedFlow<DeleteProductResponseData> get() = _deleteProductFlow

    private val _messageDeleteProductFlow = MutableSharedFlow<String>()
    val messageDeleteProductFlow : SharedFlow<String> get() = _messageDeleteProductFlow

    private val _errorDeleteProductFlow = MutableSharedFlow<Throwable>()
    val errorDeleteProductFlow : SharedFlow<Throwable> get() = _errorDeleteProductFlow

    fun deleteProduct(id:Int){
        deleteProductUseCase.execute(id = id).onEach {
            when(it){
                is ResultData.Success ->{
                    _deleteProductFlow.emit(it.data)
                }
                is ResultData.Message ->{
                    _messageDeleteProductFlow.emit(it.message)
                }
                is ResultData.Error ->{
                    _errorDeleteProductFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getAllCategories(){
        getAllCategoriesUseCase.execute().onEach {
            when(it){
                is ResultData.Success ->{
                    _getAllCategoriesFlow.emit(it.data)
                }
                is ResultData.Message ->{
                    _messageGetAllCategoriesFlow.emit(it.message)
                }
                is ResultData.Error ->{
                    _errorGetAllCategoriesFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getAllProductByCategory(id:Int){
        getAllProductsByCategoryUseCase.execute(id = id).onEach {
            when(it){
                is ResultData.Success ->{
                    _getAllProduct.emit(it.data)
                }
                is ResultData.Message -> {
                    _messageGetAllProduct.emit(it.message)
                }
                is ResultData.Error ->{
                    _errorGetAllProduct.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }


    fun deleteCategory(id:Int){
        deleteCategoryUseCase.execute(id).onEach {
            when(it){
                is ResultData.Success->{
                    _deleteCategoryFlow.emit(it.data)
                }
                is ResultData.Message->{
                    _messageDeleteCategory.emit(it.message)
                }
                is ResultData.Error->{
                    _errorDeleteCategory.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}