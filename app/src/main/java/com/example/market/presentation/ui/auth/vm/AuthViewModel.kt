package com.example.market.presentation.ui.auth.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.market.data.models.LoginRequestData
import com.example.market.data.models.LoginResponseData
import com.example.market.data.models.RegistrationRequestData
import com.example.market.data.models.ResultData
import com.example.market.domain.usecase.login.auth.AuthorizationUseCase
import com.example.market.domain.usecase.login.registration.RegistrationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase,
    private val authorizationUseCase: AuthorizationUseCase
):ViewModel() {

    //Registration

    private val _registrationFlow = MutableSharedFlow<LoginResponseData?>()
    val registrationFlow : SharedFlow<LoginResponseData?> get() = _registrationFlow
    
    private val _messageRegistration = MutableSharedFlow<String>()
    val messageRegistration : SharedFlow<String> get() = _messageRegistration

    private val _errorRegistration = MutableSharedFlow<Throwable>()
    val errorRegistration : SharedFlow<Throwable> get() = _errorRegistration

    fun registration(body:RegistrationRequestData){
        registrationUseCase.execute(body = body).onEach {
            when(it){
                is ResultData.Success->{
                    _registrationFlow.emit(it.data)
                }
                is ResultData.Message->{
                    _messageRegistration.emit(it.message)
                }
                is ResultData.Error ->{
                    _errorRegistration.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }


    //Authorization

    private val _authorizationFlow= MutableSharedFlow<LoginResponseData?>()
    val authorizationFlow: SharedFlow<LoginResponseData?> get() = _authorizationFlow

    private val _messageAuthorizationFlow = MutableSharedFlow<String>()
    val messageAuthorizationFlow:SharedFlow<String> get() = _messageAuthorizationFlow

    private val _errorAuthorizationFlow = MutableSharedFlow<Throwable>()
    val errorAuthorizationFlow : SharedFlow<Throwable> get() = _errorAuthorizationFlow

    fun authorization(body : LoginRequestData){
        authorizationUseCase.execute(body = body).onEach {
            when(it){
                is ResultData.Success->{
                    _authorizationFlow.emit(it.data)
                }
                is ResultData.Message->{
                    _messageAuthorizationFlow.emit(it.message)
                }
                is ResultData.Error->{
                    _errorAuthorizationFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}