package com.kikii.smarttsassignment.data.repository.composite_repo.auth_dispatch

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.kikii.smarttsassignment.data.common.ResultData
import com.kikii.smarttsassignment.data.model.DispatchModel
import com.kikii.smarttsassignment.data.repository.default_repo.auth.AuthRepository
import com.kikii.smarttsassignment.data.repository.default_repo.dispatch.DispatchRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CompositeAuthDispatchRepository @Inject constructor(
    private val authRepository: AuthRepository,
    private val dispatchRepository: DispatchRepository,
    private val connectivityManager: ConnectivityManager  // Injected ConnectivityManager
) : AuthDispatchRepository {
//
//    override suspend fun fetchLatestDispatches(date: String): Flow<ResultData<List<DispatchModel?>>> {
//        // Attempt to get the JWT token from the auth repository
//        val jwt = authRepository.getJwt().firstOrNull()
//
//        // Update the auth model if JWT is not available
//        if (jwt == null) {
//            authRepository.updateAuthModel()
//        }
//
//        // Check if JWT is available and Wi-Fi is connected
//        if (jwt != null && isWifiConnected()) {
//            //return dispatchRepository.fetchLatestDispatch(jwt, date)
//            val result = dispatchRepository.fetchLatestDispatch(jwt, date).firstOrNull()
//            when (result) {
//                is ResultData.SuccessData -> {
//                    // If the fetch was successful, return the fetched dispatch models
//                    return flow{
//                        emit(ResultData.SuccessData(result.data))
//                    }
//                }
//                is ResultData.ErrorData -> {
//                    // 400
//                    if (result.exception.message?.contains("400") == true) {
//                        // If the fetch failed due to an invalid JWT, update the auth model and try again
//                        println("authRepository.updateAuthModel()")
//                        authRepository.updateAuthModel()
//                        println("authRepository.getJwt().firstOrNull()")
//                        val newJwt = authRepository.getJwt().firstOrNull()
//                        println("newJwt: $newJwt")
//                        if (newJwt != null) {
//                            println("dispatchRepository.fetchLatestDispatch(newJwt, date) : ${dispatchRepository.fetchLatestDispatch(newJwt, date)}")
//                            return dispatchRepository.fetchLatestDispatch(newJwt, date)
//                        }
//                    }
//                    // If the fetch failed, return locally stored dispatch models
//                    return dispatchRepository.findDispatchesByDate(date)
//                }
//
//                null -> TODO()
//            }
//        }
//
//        // If no JWT or no Wi-Fi, return locally stored dispatch models
//        return dispatchRepository.findDispatchesByDate(date)
//    }
override suspend fun fetchLatestDispatches(date: String): Flow<ResultData<List<DispatchModel?>>> {
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
            val result = dispatchRepository.fetchLatestDispatch(jwt!!, date).firstOrNull()
            when (result) {
                is ResultData.SuccessData -> {
                    // If the fetch was successful, emit the fetched dispatch models
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
                                            dispatchRepository.fetchLatestDispatch(jwt!!, date).firstOrNull()
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
                        // If fetch failed for reasons other than invalid JWT, return locally stored dispatch models
                        emitAll(dispatchRepository.findDispatchesByDate(date))
                    }
                }
                null -> emit(ResultData.ErrorData(Exception("Dispatch fetch result is null")))
            }
        } else {
            // If no JWT or no Wi-Fi, return locally stored dispatch models
            emitAll(dispatchRepository.findDispatchesByDate(date))
        }
    }
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