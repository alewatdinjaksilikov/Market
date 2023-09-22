package com.bizlergroup.stockcontrol.domain.usecase.editProfile.impl

import com.bizlergroup.stockcontrol.data.models.EditProfileRequestData
import com.bizlergroup.stockcontrol.data.models.LoginResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.domain.repository.main.MainRepository
import com.bizlergroup.stockcontrol.domain.usecase.editProfile.EditProfileUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EditProfileUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):EditProfileUseCase {
    override fun execute(body: EditProfileRequestData): Flow<ResultData<LoginResponseData>>
        = mainRepository.editProfile(body = body)
}