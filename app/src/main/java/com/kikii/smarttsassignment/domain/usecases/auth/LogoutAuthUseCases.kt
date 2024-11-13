package com.kikii.smarttsassignment.domain.usecases.auth

import com.kikii.smarttsassignment.data.repository.default_repo.auth.AuthRepository
import com.kikii.smarttsassignment.domain.di.coroutine.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LogoutAuthUseCases @Inject constructor(
    private val authRepository: AuthRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend fun logout(email: String, password: String): Flow<Boolean> =
        withContext(defaultDispatcher) {
            authRepository.logout()
        }

}