package com.kikii.smarttsassignment.domain.usecases.auth

import com.kikii.smarttsassignment.data.common.ResultData
import com.kikii.smarttsassignment.data.model.AuthModel
import com.kikii.smarttsassignment.data.repository.default_repo.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAuthUseCases @Inject constructor(
    private val authRepository: AuthRepository
) {

    fun getAuthModel(): Flow<ResultData<AuthModel?>> =
        authRepository.currentAuthModel

    suspend fun getJwt(): Flow<String?> =
        authRepository.getJwt()
}