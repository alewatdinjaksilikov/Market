package com.example.market.domain.repository.main

import com.example.market.data.models.*
import com.example.market.data.models.ResultData
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface MainRepository {

    fun addImage(body: MultipartBody.Part):Flow<ResultData<EditProductResponseData>>

    fun editPassword(body:EditPasswordRequestData):Flow<ResultData<EditProductResponseData>>

    fun editProfile(body:EditProfileRequestData):Flow<ResultData<LoginResponseData>>

    fun addProduct(body:AddProductRequestData): Flow<ResultData<AddProductResponseData>>

    fun editProductById(id: Int,body:EditProductRequestData):Flow<ResultData<EditProductResponseData>>

    fun deleteProduct(productId:Int):Flow<ResultData<DeleteProductResponseData>>

    fun getAllCategories():Flow<ResultData<List<CategoryResponseData>>>

    fun addCategory(body:AddCategoryRequestData):Flow<ResultData<AddCategoryResponseData>>

    fun deleteCategory(id:Int):Flow<ResultData<EditProductResponseData>>

    fun getCategoryById(id: Int):Flow<ResultData<GetCategory>>

    fun editCategory(body:AddCategoryRequestData,id: Int):Flow<ResultData<EditProductResponseData>>

    fun getAllProductByCategory(id: Int):Flow<ResultData<List<ProductResponseData>>>

    fun getAllImages():Flow<ResultData<List<ImageResponseData>>>

    fun getProductByName(name:String):Flow<ResultData<ProductResponseData>>

    fun getAllProducts():Flow<ResultData<List<ProductResponseData>>>

    fun sellProduct(body:SellProductRequestData):Flow<ResultData<EditProductResponseData>>

    fun getAllBuy():Flow<ResultData<List<MonitoringResponseData>>>

    fun getAllSale():Flow<ResultData<List<MonitoringResponseData>>>

    fun getStatistics():Flow<ResultData<StatisticsResponseData>>

    fun addAmountProduct(body : AddAmountRequestData):Flow<ResultData<EditProductResponseData?>>
}