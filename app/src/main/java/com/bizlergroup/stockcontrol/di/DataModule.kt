package com.bizlergroup.stockcontrol.di

import android.content.Context
import com.bizlergroup.stockcontrol.data.network.ApiService
import com.bizlergroup.stockcontrol.data.network.LoginApiService
import com.bizlergroup.stockcontrol.data.repository.login.LoginRepositoryImpl
import com.bizlergroup.stockcontrol.data.repository.main.MainRepositoryImpl
import com.bizlergroup.stockcontrol.data.utils.CustomInterceptor
import com.bizlergroup.stockcontrol.domain.repository.login.LoginRepository
import com.bizlergroup.stockcontrol.domain.repository.main.MainRepository
import com.bizlergroup.stockcontrol.utils.retrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

const val BASE_URL = "https://stockcontrol-v1.onrender.com/"

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideLoginApi(client: OkHttpClient):LoginApiService{
       return retrofitBuilder(BASE_URL,client).create(LoginApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApi(client:OkHttpClient):ApiService{
        return retrofitBuilder(BASE_URL,client).create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context,interceptor: HttpLoggingInterceptor):OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(CustomInterceptor()).build()
    }

    @Provides
    @Singleton
    fun provideInterceptor():HttpLoggingInterceptor{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun mainRepository(apiService:ApiService): MainRepository {
        return MainRepositoryImpl(apiService = apiService)
    }

    @Provides
    @Singleton
    fun loginRepository(loginApiService: LoginApiService):LoginRepository{
        return LoginRepositoryImpl(loginApiService = loginApiService)
    }

}