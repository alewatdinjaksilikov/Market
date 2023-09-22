package com.bizlergroup.stockcontrol.data.repository.main

import com.bizlergroup.stockcontrol.data.models.*
import com.bizlergroup.stockcontrol.data.network.ApiService
import com.bizlergroup.stockcontrol.domain.repository.main.MainRepository
import com.bizlergroup.stockcontrol.data.models.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val apiService: ApiService) : MainRepository {

    // !! - eki undew isletiw kate oni bilemen. Basinda jazilip ketilgen oni ozgertip shgaman
    override fun addImage(body: MultipartBody.Part)= flow {
        val response = apiService.addImage(body)
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch { emit(ResultData.Error(it)) }.flowOn(Dispatchers.IO)

    override fun uploadStatistics() = flow {
        val response = apiService.uploadStatistics()
        if (response.isSuccessful){
            emit(ResultData.Success(response.body()))
        }else{
            emit(ResultData.Message(response.message()))
        }
    }.catch { emit(ResultData.Error(it)) }.flowOn(Dispatchers.IO)

    override fun editPassword(body: EditPasswordRequestData)= flow {
        val response = apiService.editPassword(body = body)
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch { emit(ResultData.Error(it)) }.flowOn(Dispatchers.IO)

    override fun editProfile(body: EditProfileRequestData)= flow {
        val response = apiService.editProfile(body = body)
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch { emit(ResultData.Error(it)) }.flowOn(Dispatchers.IO)

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

    override fun getStatisticsMain() = flow {
        val response = apiService.getStatisticsMain()
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

    override fun addAmountProduct(body: AddAmountRequestData) = flow {
        val response = apiService.addAmountProduct(body = body)
        if (response.isSuccessful){
            emit(ResultData.Success(response.body()))
        }else{
            emit(ResultData.Message(response.message()))
        }
    }.catch { emit(ResultData.Error(it)) }.flowOn(Dispatchers.IO)
}