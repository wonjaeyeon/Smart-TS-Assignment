package com.kikii.smarttsassignment.domain.usecases.auth

import com.kikii.smarttsassignment.data.common.ResultData
import com.kikii.smarttsassignment.data.model.AuthModel
import com.kikii.smarttsassignment.data.repository.AuthRepository
import com.kikii.smarttsassignment.domain.di.coroutine.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LoginAuthUseCases @Inject constructor(
    private val authRepository: AuthRepository,
    @IoDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    suspend fun login(email: String, password: String): Flow<ResultData<AuthModel?>> =
        withContext(defaultDispatcher) {
            authRepository.login(email, password)
        }

}