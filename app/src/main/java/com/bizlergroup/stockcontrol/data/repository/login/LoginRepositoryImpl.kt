package com.bizlergroup.stockcontrol.data.repository.login

import com.bizlergroup.stockcontrol.data.models.LoginRequestData
import com.bizlergroup.stockcontrol.data.models.RegistrationRequestData
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.data.network.LoginApiService
import com.bizlergroup.stockcontrol.domain.repository.login.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val loginApiService: LoginApiService):LoginRepository {

    override fun login(body: LoginRequestData) = flow {
        val response = loginApiService.login(body = body)
        if (response.isSuccessful){
            emit(ResultData.Success(response.body()))
        }else{
            emit(ResultData.Message(response.message()))
        }
    }.catch { emit(ResultData.Error(it)) }.flowOn(Dispatchers.IO)

    override fun registration(body: RegistrationRequestData)= flow {
        val response = loginApiService.registration(body = body)
        if (response.isSuccessful){
            emit(ResultData.Success(response.body()))
        }else{
            emit(ResultData.Message(response.message()))
        }
    }.catch { emit(ResultData.Error(it)) }.flowOn(Dispatchers.IO)
}