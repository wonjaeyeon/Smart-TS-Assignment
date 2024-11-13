package com.kikii.smarttsassignment.data.repository.default_repo.auth

import com.kikii.smarttsassignment.data.common.ResultData
import com.kikii.smarttsassignment.data.datasource.local.SmartTsAssignmentLocalDataSource
import com.kikii.smarttsassignment.data.datasource.remote.SmartTsAssignmentRemoteDataSource
import com.kikii.smarttsassignment.data.datasource.remote.api.auth.AuthRequest
import com.kikii.smarttsassignment.data.mapper.AuthMapper
import com.kikii.smarttsassignment.data.model.AuthModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

//class DefaultAuthRepository @Inject constructor(
//    private val remoteDataSource: SmartTsAssignmentRemoteDataSource,
//    private val localDataSource: SmartTsAssignmentLocalDataSource
//) : AuthRepository {
//
//    override suspend fun login(email: String, password: String): Flow<LoginModel?> {
//
//            val loginRequest = LoginRequest(email, password)
//            val loginResponse = remoteDataSource.getLoginResponse(loginRequest)
//            val loginModel = loginResponse.first().`object`
//        return flow {
//            //authDao.saveUser(loginResponse.`object`)
//            //emit(loginModel)
//        }
//    }
//
//    override suspend fun logout(): Flow<Boolean> {
//        return flow {
//            //authDao.deleteUser()
//            emit(true)
//        }
//    }
//
//    override suspend fun getJwt(): Flow<String?> {
//        return flow {
//            //val user = authDao.getUser()
//            //emit(user?.jwt)
//        }
//    }
//}

class DefaultAuthRepository @Inject constructor(
    private val localDataSource: SmartTsAssignmentLocalDataSource,
    private val remoteDataSource: SmartTsAssignmentRemoteDataSource,
    private val authMapper: AuthMapper
) : AuthRepository {

    // Login: Try to authenticate via the remote data source and save the user info locally
    override suspend fun login(email: String, password: String): Flow<ResultData<AuthModel?>> {
        return flow {
            val authRequest = AuthRequest(email, password)
            val response = remoteDataSource.getLoginResponse(authRequest).firstOrNull()

            if (response == null) {
                emit(ResultData.ErrorData(
                    Exception("No response from server")
                ))
                return@flow
            }else{
            val responseBody = response.body()

            if (responseBody == null) {
                emit(ResultData.ErrorData(
                    Exception("No response from server or invalid response")
                    )
                )
            }

            if (responseBody?.status == 400) {
                emit(ResultData.ErrorData(
                    Exception("Invalid email or password")
                )
                )
                return@flow
            }

            // 제대로 전달받은 경우
            if (responseBody?.status == 200) {
                val user = authMapper.fromAuthResponseToAuthEntity(responseBody)
                localDataSource.createUser(user)


            emit(ResultData.SuccessData(responseBody?.`object`?.let { authMapper.fromLoginResponseToAuthModel(responseBody) }))
            }}
        }
    }

    // Logout: Clear the user's local data (token, etc.)
    override suspend fun logout(): Flow<Boolean> {
        return flow {
            // Clear token locally
            // TODO : modifiy to just DELETE ALL USERS
//            localDataSource.getUsers().forEach { user ->
//                localDataSource.deleteUser(user.loginId)
//            }
            localDataSource.deleteAllUsers()
            emit(true)
        }
    }

    // Get Auth Model: Retrieve the user's info from local data
//    override suspend fun getAuthModel(): Flow<ResultData<AuthModel?>> {
//        return flow {
//            val user = localDataSource.getUsers().firstOrNull()
////            emit(user?.let { authMapper.fromAuthEntityToAuthModel(it) })
//            if (user == null) {
//                emit(ResultData.ErrorData(
//                    Exception("No user found")
//                ))
//            } else {
//                emit(ResultData.SuccessData(authMapper.fromAuthEntityToAuthModel(user)))
//            }
//        }
//    }
    override val currentAuthModel: Flow<ResultData<AuthModel>> = localDataSource.currentUsers.map {
        if (it.isEmpty()) {
            ResultData.ErrorData(Exception("No user found"))
        } else {
            ResultData.SuccessData(authMapper.fromAuthEntityToAuthModel(it.first()))
        }
    }

    // Update Auth Model: Update the user's info by fetching the latest data from the server
    override suspend fun updateAuthModel(): Flow<ResultData<AuthModel?>> {
        return flow {
            val user = localDataSource.currentUsers.firstOrNull()
            if (user.isNullOrEmpty()) {
                emit(ResultData.ErrorData(
                    Exception("No user found")
                ))
                return@flow
            }

            val loginId = user.first().loginId
            val token = user.first().token

            val response = remoteDataSource.getLoginResponse(
                AuthRequest(loginId, token)
            ).firstOrNull()
            if (response == null) {
                emit(ResultData.ErrorData(
                    Exception("No response from server")
                ))
                return@flow
            }

            val responseBody = response.body()
            if (responseBody == null) {
                emit(ResultData.ErrorData(
                    Exception("No response from server or invalid response")
                ))
                return@flow
            }

            if (responseBody.status == 400) {
                emit(ResultData.ErrorData(
                    Exception("Invalid token")
                ))
                return@flow
            }

            if (responseBody.status == 200) {
                val updatedUser = authMapper.fromAuthResponseToAuthEntity(responseBody)
                localDataSource.updateUser(updatedUser)
                emit(ResultData.SuccessData(authMapper.fromLoginResponseToAuthModel(responseBody)))
            }
        }
    }

    // Get JWT Token: Retrieve the token from local data
    override suspend fun getJwt(): Flow<String?> {
        return flow {
            val user = localDataSource.currentUsers.firstOrNull()
            if (user.isNullOrEmpty()){
                emit(null)
            }else{
                emit(user.firstOrNull()?.token)
            }
            //emit(user?.token)
        }
    }
}