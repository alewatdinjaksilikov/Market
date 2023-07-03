package com.example.market.data.network

import com.example.market.data.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @POST("/api/v1/products")
    suspend fun addProduct(@Body body: AddProductRequestData): Response<Any>

    @PUT("/api/v1/products/{productId}")
    suspend fun editProductById(
        @Body body: EditProductRequestData,
        @Path("productId") productId: Int
    ): Response<Any>

    @DELETE("/api/v1/products/{productId}")
    suspend fun deleteProduct(@Path("productId") productId: Int): Response<Any>

    @GET("/api/v1/categories")
    suspend fun getAllCategories(): Response<List<CategoryResponseData>>

    @POST("/api/v1/categories")
    suspend fun addCategory(@Body body: AddCategoryRequestData): Response<AddCategoryResponseData>

    @DELETE("/api/v1/categories/{categoryId}")
    suspend fun deleteCategory(@Path("categoryId") id: Int): Response<Any>

    @PUT("/api/v1/categories/{categoryId}")
    suspend fun editCategory(
        @Body body: AddCategoryRequestData,
        @Path("categoryId") id: Int
    ): Response<Any>

    @GET("/api/v1/categories/{categoryId}/products")
    suspend fun getAllProductByCategory(@Path("categoryId") id: Int): Response<List<Product>>

    @GET("/api/v1/images")
    suspend fun getAllImages():Response<List<ImageResponseData>>
}