package com.bizlergroup.stockcontrol.domain.usecase.editPassword.impl

import com.bizlergroup.stockcontrol.data.models.EditPasswordRequestData
import com.bizlergroup.stockcontrol.data.models.EditProductResponseData
import com.bizlergroup.stockcontrol.data.models.ResultData
import com.bizlergroup.stockcontrol.domain.repository.main.MainRepository
import com.bizlergroup.stockcontrol.domain.usecase.editPassword.EditPasswordUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EditPasswordUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    EditPasswordUseCase {
    override fun execute(body: EditPasswordRequestData): Flow<ResultData<EditProductResponseData>>
        = mainRepository.editPassword(body = body)
}