package com.example.market.domain.repository

import com.example.market.data.models.*
import com.example.market.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun addProduct(body:AddProductRequestData): Flow<ResultData<AddProductResponseData>>

    fun editProductById(id: Int,body:EditProductRequestData):Flow<ResultData<EditProductResponseData>>

    fun deleteProduct(productId:Int):Flow<ResultData<DeleteProductResponseData>>

    fun getAllCategories():Flow<ResultData<List<CategoryResponseData>>>

    fun addCategory(body:AddCategoryRequestData):Flow<ResultData<AddCategoryResponseData>>

    fun deleteCategory(id:Int):Flow<ResultData<Any>>

    fun editCategory(body:AddCategoryRequestData,id: Int):Flow<ResultData<Any>>

    fun getAllProductByCategory(id: Int):Flow<ResultData<List<ProductResponseData>>>

    fun getAllImages():Flow<ResultData<List<ImageResponseData>>>

    fun getProductByName(name:String):Flow<ResultData<ProductResponseData>>

    fun getAllProducts():Flow<ResultData<List<ProductResponseData>>>

    fun sellProduct(body:SellProductRequestData):Flow<ResultData<EditProductResponseData>>

    fun getAllBuy():Flow<ResultData<List<MonitoringResponseData>>>

    fun getAllSale():Flow<ResultData<List<MonitoringResponseData>>>

    fun getStatistics():Flow<ResultData<StatisticsResponseData>>
}