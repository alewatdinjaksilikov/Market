package com.example.market.data.repository

import androidx.core.os.bundleOf
import com.example.market.data.models.*
import com.example.market.data.network.ApiService
import com.example.market.domain.repository.MainRepository
import com.example.market.data.models.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val apiService: ApiService) : MainRepository {


    override fun addProduct(body: AddProductRequestData) = flow {
        val response = apiService.addProduct(body = body)
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch { emit(ResultData.Error(it)) }.flowOn(Dispatchers.IO)

    override fun editProductById(
        id: Int,
        body: EditProductRequestData
    ) = flow {
        val response = apiService.editProductById(body = body, productId = id)
        if (response.isSuccessful){
            emit(ResultData.Success(response.body()!!))
        }else{
            emit(ResultData.Message(response.message()))
        }
    }.catch { emit(ResultData.Error(it)) }.flowOn(Dispatchers.IO)

    override fun deleteProduct(productId: Int) = flow {
        val response = apiService.deleteProduct(productId = productId)
        if (response.isSuccessful){
            emit(ResultData.Success(response.body()!!))
        }else{
            emit(ResultData.Message(response.message()))
        }
    }.catch { emit(ResultData.Error(it)) }.flowOn(Dispatchers.IO)

    override fun getAllCategories() = flow {
        val response = apiService.getAllCategories()
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch { emit(ResultData.Error(it)) }.flowOn(Dispatchers.IO)

    override fun addCategory(body: AddCategoryRequestData) = flow {
        val response = apiService.addCategory(body = body)
        if (response.isSuccessful){
            emit(ResultData.Success(response.body()!!))
        }else{
            emit(ResultData.Message(response.message()))
        }
    }.catch { emit(ResultData.Error(it)) }.flowOn(Dispatchers.IO)

    override fun deleteCategory(id: Int) = flow {
        val response = apiService.deleteCategory(id = id)
        if (response.isSuccessful) emit(ResultData.Success(response.body()!!))
        else emit(ResultData.Message(response.message()))
    }.catch { emit(ResultData.Error(it)) }.flowOn(Dispatchers.IO)

    override fun getCategoryById(id: Int) = flow{
        val response = apiService.getCategoryById(id = id)
        if (response.isSuccessful){
            emit(ResultData.Success(response.body()!!))
        }else{
            emit(ResultData.Message(response.message()))
        }
    }.catch { emit(ResultData.Error(it)) }.flowOn(Dispatchers.IO)

    override fun editCategory(body: AddCategoryRequestData, id: Int) = flow {
        val response = apiService.editCategory(body = body, id = id)
        if (response.isSuccessful) emit(ResultData.Success(response.body()!!))
        else emit(ResultData.Message(response.message()))
    }.catch { emit(ResultData.Error(it)) }.flowOn(Dispatchers.IO)

    override fun getAllProductByCategory(id: Int) = flow {
        val response = apiService.getAllProductByCategory(id = id)
        if (response.isSuccessful) emit(ResultData.Success(response.body()!!))
        else emit(ResultData.Message(response.message()))
    }.catch { emit(ResultData.Error(it)) }.flowOn(Dispatchers.IO)

    override fun getAllImages() = flow {
        val response = apiService.getAllImages()
        if (response.isSuccessful){
            emit(ResultData.Success(response.body()!!))
        }else{
            emit(ResultData.Message(response.message()))
        }
    }.catch { emit(ResultData.Error(it)) }.flowOn(Dispatchers.IO)

    override fun getProductByName(name: String) = flow {
        val response = apiService.getProductByName(name = name)
        if (response.isSuccessful){
            emit(ResultData.Success(response.body()!!))
        }else{
            emit(ResultData.Message(response.message()))
        }
    }.catch { emit(ResultData.Error(it)) }.flowOn(Dispatchers.IO)

    override fun getAllProducts() = flow {
        val response = apiService.getAllProducts()
        if (response.isSuccessful){
            emit(ResultData.Success(response.body()!!))
        }else{
            emit(ResultData.Message(response.message()))
        }
    }.catch { emit(ResultData.Error(it)) }.flowOn(Dispatchers.IO)

    override fun sellProduct(body: SellProductRequestData) = flow {
        val response = apiService.sellProduct(body = body)
        if (response.isSuccessful){
            emit(ResultData.Success(response.body()!!))
        }else{
            emit(ResultData.Message(response.message()))
        }
    }.catch { emit(ResultData.Error(it)) }.flowOn(Dispatchers.IO)

    override fun getAllBuy() = flow {
        val response = apiService.getAllBuy()
        if (response.isSuccessful){
            emit(ResultData.Success(response.body()!!))
        }else{
            emit(ResultData.Message(response.message()))
        }
    }.catch { emit(ResultData.Error(it)) }.flowOn(Dispatchers.IO)

    override fun getAllSale() = flow {
        val response = apiService.getAllSale()
        if (response.isSuccessful){
            emit(ResultData.Success(response.body()!!))
        }else{
            emit(ResultData.Message(response.message()))
        }
    }.catch { emit(ResultData.Error(it)) }.flowOn(Dispatchers.IO)

    override fun getStatistics() = flow {
        val response = apiService.getStatistics()
        if (response.isSuccessful){
            emit(ResultData.Success(response.body()!!))
        }else{
            emit(ResultData.Message(response.message()))
        }
    }.catch { emit(ResultData.Error(it)) }.flowOn(Dispatchers.IO)
}