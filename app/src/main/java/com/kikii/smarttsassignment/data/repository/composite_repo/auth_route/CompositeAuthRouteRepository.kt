package com.kikii.smarttsassignment.data.repository.composite_repo.auth_route

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.kikii.smarttsassignment.data.common.ResultData
import com.kikii.smarttsassignment.data.model.RouteModel
import com.kikii.smarttsassignment.data.repository.default_repo.auth.AuthRepository
import com.kikii.smarttsassignment.data.repository.default_repo.route.RouteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class CompositeAuthRouteRepository @Inject constructor(
    private val authRepository: AuthRepository,
    private val routeRepository: RouteRepository,
    private val connectivityManager: ConnectivityManager  // Injected ConnectivityManager
) : AuthRouteRepository {

    override suspend fun fetchLatestRoute(): Flow<ResultData<RouteModel?>> {
        val jwt = authRepository.getJwt().firstOrNull()

        if (jwt == null) {
            authRepository.updateAuthModel()
        }

        if (jwt != null && isWifiConnected()) {
            return routeRepository.fetchLatestRoute(jwt)
        }

        return routeRepository.localRouteModel
    }

    override val localRouteModel: RouteRepository
        get() = routeRepository

    private fun isWifiConnected(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
    }

}