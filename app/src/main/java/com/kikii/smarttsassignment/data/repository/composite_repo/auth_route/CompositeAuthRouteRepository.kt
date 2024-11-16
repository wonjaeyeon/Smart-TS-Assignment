package com.kikii.smarttsassignment.data.repository.composite_repo.auth_route

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.kikii.smarttsassignment.data.common.ResultData
import com.kikii.smarttsassignment.data.model.RouteModel
import com.kikii.smarttsassignment.data.repository.default_repo.auth.AuthRepository
import com.kikii.smarttsassignment.data.repository.default_repo.route.RouteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CompositeAuthRouteRepository @Inject constructor(
    private val authRepository: AuthRepository,
    private val routeRepository: RouteRepository,
    private val connectivityManager: ConnectivityManager  // Injected ConnectivityManager
) : AuthRouteRepository {

//    override suspend fun fetchLatestRoute(): Flow<ResultData<RouteModel?>> {
//        val jwt = authRepository.getJwt().firstOrNull()
//
//        if (jwt == null) {
//            authRepository.updateAuthModel()
//        }
//
//        if (jwt != null && isWifiConnected()) {
//            return routeRepository.fetchLatestRoute(jwt)
//        }
//
//        return routeRepository.localRouteModel
//    }
override suspend fun fetchLatestRoute(): Flow<ResultData<RouteModel?>> {
    return flow {
        // Attempt to get the JWT token from the auth repository
        var jwt = authRepository.getJwt().firstOrNull()

        // Update the auth model if JWT is not available
        if (jwt == null) {
            println("JWT not found. Updating auth model...")
            authRepository.updateAuthModel().collect { result ->
                when (result) {
                    is ResultData.SuccessData -> {
                        println("Auth model updated successfully.")
                        jwt = authRepository.getJwt().firstOrNull()
                        println("New JWT: $jwt")
                    }
                    is ResultData.ErrorData -> {
                        println("Failed to update auth model: ${result.exception.message}")
                        emit(ResultData.ErrorData(result.exception))
                        return@collect
                    }
                }
            }
        }

        // Check if JWT is available and Wi-Fi is connected
        if (jwt != null && isWifiConnected()) {
            val result = routeRepository.fetchLatestRoute(jwt!!).firstOrNull()
            when (result) {
                is ResultData.SuccessData -> {
                    // If the fetch was successful, emit the fetched route model
                    emit(ResultData.SuccessData(result.data))
                }
                is ResultData.ErrorData -> {
                    // If the fetch failed due to an invalid JWT, update the auth model and try again
                    if (result.exception.message?.contains("400") == true) {
                        println("Invalid JWT detected. Updating auth model...")
                        authRepository.updateAuthModel().collect { updateResult ->
                            when (updateResult) {
                                is ResultData.SuccessData -> {
                                    jwt = authRepository.getJwt().firstOrNull()
                                    println("New JWT after update: $jwt")
                                    if (jwt != null) {
                                        val retryResult =
                                            routeRepository.fetchLatestRoute(jwt!!).firstOrNull()
                                        emit(retryResult ?: ResultData.ErrorData(Exception("Retry failed")))
                                    } else {
                                        emit(ResultData.ErrorData(Exception("Failed to retrieve new JWT")))
                                    }
                                }
                                is ResultData.ErrorData -> {
                                    emit(ResultData.ErrorData(updateResult.exception))
                                    return@collect
                                }
                            }
                        }
                    } else {
                        // If fetch failed for reasons other than invalid JWT, return locally stored route model
                        emitAll(routeRepository.localRouteModel)
                    }
                }
                null -> emit(ResultData.ErrorData(Exception("Route fetch result is null")))
            }
        } else {
            // If no JWT or no Wi-Fi, return locally stored route model
            emitAll(routeRepository.localRouteModel)
        }
    }
}


    override val localRouteModel: Flow<ResultData<RouteModel?>>
         = routeRepository.localRouteModel

    private fun isWifiConnected(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
    }

}