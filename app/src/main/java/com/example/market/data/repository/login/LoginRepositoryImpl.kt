package com.example.market.data.repository.login

import com.example.market.data.models.LoginRequestData
import com.example.market.data.models.RegistrationRequestData
import com.example.market.data.models.ResultData
import com.example.market.data.network.LoginApiService
import com.example.market.domain.repository.login.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val loginApiService: LoginApiService):LoginRepository {

    override fun login(body: LoginRequestData) = flow {
        val response = loginApiService.login(body = body)
        if (response.isSuccessful){
            emit(ResultData.Success(response.body()!!))
        }else{
            emit(ResultData.Message(response.message()))
        }
    }.catch { emit(ResultData.Error(it)) }.flowOn(Dispatchers.IO)

    override fun registration(body: RegistrationRequestData)= flow {
        val response = loginApiService.registration(body = body)
        if (response.isSuccessful){
            emit(ResultData.Success(response.body()!!))
        }else{
            emit(ResultData.Message(response.message()))
        }
    }.catch { emit(ResultData.Error(it)) }.flowOn(Dispatchers.IO)
}