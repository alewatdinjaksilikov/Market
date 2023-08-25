package com.example.market.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.market.data.network.ApiService
import com.example.market.data.network.LoginApiService
import com.example.market.data.repository.login.LoginRepositoryImpl
import com.example.market.data.repository.main.MainRepositoryImpl
import com.example.market.data.utils.CustomInterceptor
import com.example.market.domain.repository.login.LoginRepository
import com.example.market.domain.repository.main.MainRepository
import com.example.market.utils.retrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton
import kotlin.math.log

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideLoginApi(client: OkHttpClient):LoginApiService{
       return retrofitBuilder("http://stockcontrol.uz",client).create(LoginApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApi(client:OkHttpClient):ApiService{
        return retrofitBuilder("http://stockcontrol.uz",client).create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context,interceptor: HttpLoggingInterceptor):OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(ChuckerInterceptor(context))
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