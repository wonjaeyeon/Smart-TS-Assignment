package com.kikii.smarttsassignment.domain.usecases.dispatch

import com.kikii.smarttsassignment.data.repository.composite_repo.auth_dispatch.AuthDispatchRepository
import com.kikii.smarttsassignment.domain.di.coroutine.DefaultDispatcher
import com.kikii.smarttsassignment.domain.usecases.date.DateFormatUseCases
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchDispatchUseCases @Inject constructor(
    private val authDispatchRepository: AuthDispatchRepository,
    private val dateFormatUseCases: DateFormatUseCases,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {
    suspend fun fetchLatestDispatches(date: String) = withContext(defaultDispatcher) {authDispatchRepository.fetchLatestDispatches(date)}
}