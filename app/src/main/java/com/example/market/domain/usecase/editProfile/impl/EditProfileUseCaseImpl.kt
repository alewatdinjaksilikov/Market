package com.example.market.domain.usecase.editProfile.impl

import com.example.market.data.models.EditProfileRequestData
import com.example.market.data.models.LoginResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.main.MainRepository
import com.example.market.domain.usecase.editProfile.EditProfileUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EditProfileUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):EditProfileUseCase {
    override fun execute(body: EditProfileRequestData): Flow<ResultData<LoginResponseData>>
        = mainRepository.editProfile(body = body)
}