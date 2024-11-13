package com.kikii.smarttsassignment.data.repository

import com.kikii.smarttsassignment.data.common.ResultData
import com.kikii.smarttsassignment.data.model.RouteModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class CompositeAuthRouteRepository @Inject constructor(
    private val authRepository: AuthRepository,
    private val routeRepository: RouteRepository
) : AuthRouteRepository {

    override suspend fun fetchLatestRoute(): Flow<ResultData<RouteModel?>> {
        val jwt = authRepository.getJwt().firstOrNull()

        if (jwt == null) {
            authRepository.updateAuthModel()
        }

        if (jwt != null) {
            return routeRepository.fetchLatestRoute(jwt)
        }

        return routeRepository.localRouteModel
    }

    override val localRouteModel: RouteRepository
        get() = routeRepository
}