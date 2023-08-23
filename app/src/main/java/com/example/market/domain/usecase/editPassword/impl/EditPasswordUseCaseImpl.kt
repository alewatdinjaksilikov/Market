package com.example.market.domain.usecase.editPassword.impl

import com.example.market.data.models.EditPasswordRequestData
import com.example.market.data.models.EditProductResponseData
import com.example.market.data.models.ResultData
import com.example.market.domain.repository.main.MainRepository
import com.example.market.domain.usecase.editPassword.EditPasswordUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EditPasswordUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):
    EditPasswordUseCase {
    override fun execute(body: EditPasswordRequestData): Flow<ResultData<EditProductResponseData>> {
        return mainRepository.editPassword(body = body)
    }
}