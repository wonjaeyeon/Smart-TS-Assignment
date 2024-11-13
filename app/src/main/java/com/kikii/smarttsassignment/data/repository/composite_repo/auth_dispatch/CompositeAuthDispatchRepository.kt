package com.kikii.smarttsassignment.data.repository.composite_repo.auth_dispatch

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.kikii.smarttsassignment.data.common.ResultData
import com.kikii.smarttsassignment.data.model.DispatchModel
import com.kikii.smarttsassignment.data.repository.default_repo.auth.AuthRepository
import com.kikii.smarttsassignment.data.repository.default_repo.dispatch.DispatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class CompositeAuthDispatchRepository @Inject constructor(
    private val authRepository: AuthRepository,
    private val dispatchRepository: DispatchRepository,
    private val connectivityManager: ConnectivityManager  // Injected ConnectivityManager
) : AuthDispatchRepository {

    override suspend fun fetchLatestDispatches(date: String): Flow<ResultData<List<DispatchModel?>>> {
        // Attempt to get the JWT token from the auth repository
        val jwt = authRepository.getJwt().firstOrNull()

        // Update the auth model if JWT is not available
        if (jwt == null) {
            authRepository.updateAuthModel()
        }

        // Check if JWT is available and Wi-Fi is connected
        if (jwt != null && isWifiConnected()) {
            return dispatchRepository.fetchLatestDispatch(jwt, date)
        }

        // If no JWT or no Wi-Fi, return locally stored dispatch models
        return dispatchRepository.localDispatchModels
    }

    override val localDispatchModels: Flow<ResultData<List<DispatchModel>>>
        get() = dispatchRepository.localDispatchModels

    // Helper function to check if Wi-Fi is connected
    private fun isWifiConnected(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
    }
}