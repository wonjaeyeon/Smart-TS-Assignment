package com.kikii.smarttsassignment.data.repository.default_repo.auth

import com.kikii.smarttsassignment.data.common.ResultData
import com.kikii.smarttsassignment.data.datasource.local.SmartTsAssignmentLocalDataSource
import com.kikii.smarttsassignment.data.datasource.remote.SmartTsAssignmentRemoteDataSource
import com.kikii.smarttsassignment.data.datasource.remote.api.auth.AuthRequest
import com.kikii.smarttsassignment.data.mapper.AuthMapper
import com.kikii.smarttsassignment.data.model.AuthModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DefaultAuthRepository @Inject constructor(
    private val localDataSource: SmartTsAssignmentLocalDataSource,
    private val remoteDataSource: SmartTsAssignmentRemoteDataSource,
    private val authMapper: AuthMapper
) : AuthRepository {

    // Login: Authenticate the user with the server
    override suspend fun login(email: String, password: String): Flow<ResultData<AuthModel?>> {
        return flow {
            try {
                val authRequest = AuthRequest(email, password)
                val response = remoteDataSource.getLoginResponse(authRequest).firstOrNull()

                if (response == null) {
                    emit(ResultData.ErrorData(Exception("No response from server")))
                    return@flow
                }

                val responseBody = response.body()
                if (responseBody == null) {
                    emit(ResultData.ErrorData(Exception("No response from server or invalid response")))
                    return@flow
                }

                when (responseBody.status) {
                    400 -> emit(ResultData.ErrorData(Exception("Invalid email or password")))
                    500 -> emit(ResultData.ErrorData(Exception("Server Error")))
                    200 -> {
                        val user = authMapper.fromAuthResponseToAuthEntity(responseBody, password)
                        localDataSource.createUser(user)
                        emit(ResultData.SuccessData(responseBody.`object`?.let {
                            authMapper.fromLoginResponseToAuthModel(responseBody)
                        }))
                    }

                    else -> emit(ResultData.ErrorData(Exception("Unexpected response status: ${responseBody.status}")))
                }
            } catch (e: java.net.ConnectException) {
                emit(ResultData.ErrorData(Exception("Failed to connect to the server. Please check your connection.")))
            } catch (e: Exception) {
                emit(ResultData.ErrorData(Exception("An unexpected error occurred: ${e.message}")))
            }
        }
    }

    // Logout: Clear the user's local data (token, etc.)
    override suspend fun logout(): Flow<Boolean> {
        return flow {
            // Clear token locally
            // TODO : modifiy to just DELETE ALL USERS
            localDataSource.deleteAllUsers()
            emit(true)
        }
    }

    override val currentAuthModel: Flow<ResultData<AuthModel>> = localDataSource.currentUsers.map {
        if (it.isEmpty()) {
            ResultData.ErrorData(Exception("No user found"))
        } else {
            ResultData.SuccessData(authMapper.fromAuthEntityToAuthModel(it.first()))
        }
    }

    // Update Auth Model: Update the user's info by fetching the latest data from the server
//    override suspend fun updateAuthModel(): Flow<ResultData<AuthModel?>> {
//        println("updateAuthModel ---------------------------------")
//        return flow {
//            try {
//                println("updateAuthModel try ---------------------------------")
//                val user = localDataSource.currentUsers.firstOrNull()
//                if (user.isNullOrEmpty()) {
//                    println("No user found")
//
//                    emit(
//                        ResultData.ErrorData(
//                            Exception("No user found")
//                        )
//                    )
//                    return@flow
//                }
//
//                val loginId = user.first().loginId
//                //val token = user.first().token
//                val password = user.first().password
//
//                val response = remoteDataSource.getLoginResponse(
//                    AuthRequest(loginId, password)
//                ).firstOrNull()
//
//                println("response: $response")
//                println("response.body().object: ${response?.body()?.`object`}")
//                println("response.body().object.token: ${response?.body()?.`object`?.token}")
//
//                if (response == null) {
//                    println("No response from server")
//
//                    emit(
//                        ResultData.ErrorData(
//                            Exception("No response from server")
//                        )
//                    )
//                    return@flow
//                }
//
//                val responseBody = response.body()
//                if (responseBody == null) {
//                    println("No response from server or invalid response")
//
//                    emit(
//                        ResultData.ErrorData(
//                            Exception("No response from server or invalid response")
//                        )
//                    )
//                    return@flow
//                }
//
//                if (responseBody.status == 400) {
//                    println("Invalid token")
//
//                    emit(
//                        ResultData.ErrorData(
//                            Exception("Invalid token")
//                        )
//                    )
//                    return@flow
//                }
//
//                if (responseBody.status == 200) {
//
//                    println("User updated")
//                    val updatedUser = authMapper.fromAuthResponseToAuthEntity(responseBody, password)
//                    localDataSource.updateUser(updatedUser)
//                    emit(ResultData.SuccessData(authMapper.fromLoginResponseToAuthModel(responseBody)))
//                }
//            } catch (e: java.net.ConnectException) {
//                println("Failed to connect to the server. Please check your connection.")
//
//                emit(ResultData.ErrorData(Exception("Failed to connect to the server. Please check your connection.")))
//            } catch (e: Exception) {
//                println("An unexpected error occurred: ${e.message}")
//
//                emit(ResultData.ErrorData(Exception("An unexpected error occurred: ${e.message}")))
//            }
//        }
//    }

    override suspend fun updateAuthModel(): Flow<ResultData<AuthModel?>> {
        println("updateAuthModel ---------------------------------")
        return flow {
            val user = localDataSource.currentUsers.firstOrNull()
            if (user.isNullOrEmpty()) {
                println("No user found")
                emit(ResultData.ErrorData(Exception("No user found")))
                return@flow // Ensure flow completion for this branch
            }

            val loginId = user.first().loginId
            println("loginId: $loginId")
            val password = user.first().password
            println("password: $password")

            val response = remoteDataSource.getLoginResponse(
                AuthRequest(loginId, password)
            ).firstOrNull()

            if (response == null) {
                println("No response from server")
                emit(ResultData.ErrorData(Exception("No response from server")))
                return@flow // Ensure flow completion for this branch
            }

            val responseBody = response.body()
            if (responseBody == null) {
                println("No response from server or invalid response")
                emit(ResultData.ErrorData(Exception("No response from server or invalid response")))
                return@flow // Ensure flow completion for this branch
            }

            when (responseBody.status) {
                400 -> {
                    println("Invalid token")
                    emit(ResultData.ErrorData(Exception("Invalid token")))
                }
                200 -> {
                    println("User updated")
                    val updatedUser = authMapper.fromAuthResponseToAuthEntity(responseBody, password)

                    // Update the user's info locally
                    localDataSource.updateUser(updatedUser)

                    emit(ResultData.SuccessData(authMapper.fromLoginResponseToAuthModel(responseBody)))
                }
                else -> {
                    println("Unexpected response status: ${responseBody.status}")
                    emit(ResultData.ErrorData(Exception("Unexpected response status: ${responseBody.status}")))
                }
            }
        }.catch { e ->
            println("Error occurred: ${e.message}")
            when (e) {
                is java.net.ConnectException -> emit(ResultData.ErrorData(Exception("Failed to connect to the server. Please check your connection.")))
                else -> emit(ResultData.ErrorData(Exception("An unexpected error occurred: ${e.message}")))
            }
        }
    }

    // Get JWT Token: Retrieve the token from local data
    override suspend fun getJwt(): Flow<String?> {
        return flow {
            val user = localDataSource.currentUsers.firstOrNull()
            if (user.isNullOrEmpty()) {
                emit(null)
            } else {
                emit(user.firstOrNull()?.token)
            }
            //emit(user?.token)
        }
    }
}