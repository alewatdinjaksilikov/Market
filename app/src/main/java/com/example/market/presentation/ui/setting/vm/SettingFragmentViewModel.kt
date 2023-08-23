package com.example.market.presentation.ui.setting.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.market.data.models.*
import com.example.market.domain.usecase.editPassword.EditPasswordUseCase
import com.example.market.domain.usecase.editProfile.EditProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SettingFragmentViewModel @Inject constructor(
    private val editPasswordUseCase: EditPasswordUseCase,
    private val editProfileUseCase: EditProfileUseCase
) : ViewModel() {

    private val _editPassword = MutableSharedFlow<EditProductResponseData>()
    val editPassword: SharedFlow<EditProductResponseData> get() = _editPassword

    private val _messageEditPassword = MutableSharedFlow<String>()
    val messageEditPassword: SharedFlow<String> get() = _messageEditPassword

    private val _errorEditPassword = MutableSharedFlow<Throwable>()
    val errorEditPassword: SharedFlow<Throwable> get() = _errorEditPassword

    fun editPassword(body: EditPasswordRequestData){
        editPasswordUseCase.execute(body).onEach {
            when(it){
                is ResultData.Success->{
                    _editPassword.emit(it.data)
                }
                is ResultData.Message->{
                    _messageEditPassword.emit(it.message)
                }
                is ResultData.Error->{
                    _errorEditPassword.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    private val _editProfile = MutableSharedFlow<LoginResponseData>()
    val editProfile: SharedFlow<LoginResponseData> get() = _editProfile

    private val _messageEditProfile = MutableSharedFlow<String>()
    val messageEditProfile: SharedFlow<String> get() = _messageEditProfile

    private val _errorEditProfile = MutableSharedFlow<Throwable>()
    val errorEditProfile: SharedFlow<Throwable> get() = _errorEditProfile

    fun editProfile(body: EditProfileRequestData){
        editProfileUseCase.execute(body).onEach {
            when(it){
                is ResultData.Success->{
                    _editProfile.emit(it.data)
                }
                is ResultData.Message->{
                    _messageEditProfile.emit(it.message)
                }
                is ResultData.Error->{
                    _errorEditProfile.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}
