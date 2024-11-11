package com.kikii.smarttsassignment.data.repository

import com.kikii.smarttsassignment.data.datasource.remote.SmartTsAssignmentRemoteDataSource
import com.kikii.smarttsassignment.data.datasource.remote.api.auth.LoginRequest
import com.kikii.smarttsassignment.data.model.LoginModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultAuthRepository @Inject constructor(
    private val authApi: SmartTsAssignmentRemoteDataSource,
    //private val authDao: AuthDao
) : AuthRepository {

    override suspend fun login(email: String, password: String): Flow<LoginModel?> {
        return flow {
            val loginRequest = LoginRequest(email, password)
            val loginResponse = authApi.login(loginRequest)

        }
    }

    override suspend fun logout(): Flow<Boolean> {
        return flow {
            //authDao.deleteUser()
            emit(true)
        }
    }

    override suspend fun getJwt(): Flow<String?> {
        return flow {
            //val user = authDao.getUser()
            //emit(user?.jwt)
        }
    }
}