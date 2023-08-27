package com.example.market.data.network

import com.example.market.data.models.*
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("/api/v1/products")
    suspend fun addProduct(@Body body: AddProductRequestData): Response<AddProductResponseData>

    @Multipart
    @POST("/api/v1/images")
    suspend fun addImage(@Part file: MultipartBody.Part):Response<EditProductResponseData>

    @PUT("/api/v1/products/{id}")
    suspend fun editProductById(
        @Path("id") productId: Int,
        @Body body: EditProductRequestData
    ): Response<EditProductResponseData>

    @DELETE("/api/v1/products/{productId}")
    suspend fun deleteProduct(@Path("productId") productId: Int): Response<DeleteProductResponseData>

    @GET("/api/v1/categories")
    suspend fun getAllCategories(): Response<List<CategoryResponseData>>

    @POST("/api/v1/categories")
    suspend fun addCategory(@Body body: AddCategoryRequestData): Response<AddCategoryResponseData>

    @DELETE("/api/v1/categories/{id}")
    suspend fun deleteCategory(@Path("id") id: Int): Response<EditProductResponseData>

    @PUT("/api/v1/categories/{categoryId}")
    suspend fun editCategory(
        @Body body: AddCategoryRequestData,
        @Path("categoryId") id: Int
    ): Response<EditProductResponseData>

    @GET("/api/v1/categories/{id}")
    suspend fun getCategoryById(
        @Path("id") id: Int
    ):Response<GetCategory>

    @GET("/api/v1/categories/{id}/products")
    suspend fun getAllProductByCategory(@Path("id") id: Int): Response<List<ProductResponseData>>

    @GET("/api/v1/images")
    suspend fun getAllImages():Response<List<ImageResponseData>>

    @GET("/api/v1/products/find")
    suspend fun getProductByName(
        @Query("search")name : String
    ):Response<ProductResponseData>

    @GET("/api/v1/products")
    suspend fun getAllProducts():Response<List<ProductResponseData>>

    @POST("/api/v1/products/sell")
    suspend fun sellProduct(
        @Body body : SellProductRequestData
    ):Response<EditProductResponseData>

    @GET("/api/v1/monitoring/buy")
    suspend fun getAllBuy():Response<List<MonitoringResponseData>>

    @GET("/api/v1/monitoring/sale")
    suspend fun getAllSale():Response<List<MonitoringResponseData>>

    @GET("/api/v1/statistics")
    suspend fun getStatistics():Response<StatisticsResponseData>

    @PUT("/api/v1/users/change/password")
    suspend fun editPassword(@Body body:EditPasswordRequestData):Response<EditProductResponseData>

    @PUT("/api/v1/users/change/profile")
    suspend fun editProfile(@Body body:EditProfileRequestData):Response<LoginResponseData>

    @POST("/api/v1/products/add")
    suspend fun addAmountProduct(@Body body: AddAmountRequestData):Response<EditProductResponseData>
}