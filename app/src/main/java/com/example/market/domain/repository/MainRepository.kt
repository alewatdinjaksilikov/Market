package com.example.market.domain.repository

import com.example.market.data.models.*
import com.example.market.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun addProduct(body:AddProductRequestData): Flow<ResultData<Any>>

    fun editProductById(body:EditProductRequestData,id: Int):Flow<ResultData<Any>>

    fun deleteProduct(productId:Int):Flow<ResultData<Any>>

    fun getAllCategories():Flow<ResultData<List<CategoryResponseData>>>

    fun addCategory(body:AddCategoryRequestData):Flow<ResultData<AddCategoryResponseData>>

    fun deleteCategory(id:Int):Flow<ResultData<Any>>

    fun editCategory(body:AddCategoryRequestData,id: Int):Flow<ResultData<Any>>

    fun getAllProductByCategory(id: Int):Flow<ResultData<List<Product>>>

    fun getAllImages():Flow<ResultData<List<ImageResponseData>>>
}