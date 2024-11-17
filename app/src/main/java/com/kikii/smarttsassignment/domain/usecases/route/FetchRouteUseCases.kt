package com.kikii.smarttsassignment.domain.usecases.route

import com.kikii.smarttsassignment.data.common.ResultData
import com.kikii.smarttsassignment.data.model.RouteModel
import com.kikii.smarttsassignment.data.repository.composite_repo.auth_route.AuthRouteRepository
import com.kikii.smarttsassignment.domain.di.coroutine.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchRouteUseCases @Inject constructor(
    private val authRouteRepository: AuthRouteRepository,
    @IoDispatcher private val defaultDispatcher: CoroutineDispatcher
) {
    suspend fun fetchLatestRoute(): Flow<ResultData<RouteModel?>> = withContext(defaultDispatcher) {
        authRouteRepository.fetchLatestRoute()
    }
}