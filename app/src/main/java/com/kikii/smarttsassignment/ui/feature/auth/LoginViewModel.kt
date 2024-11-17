package com.kikii.smarttsassignment.ui.feature.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kikii.smarttsassignment.data.common.ResultData
import com.kikii.smarttsassignment.data.model.AuthModel
import com.kikii.smarttsassignment.domain.usecases.auth.GetAuthUseCases
import com.kikii.smarttsassignment.domain.usecases.auth.LoginAuthUseCases
import com.kikii.smarttsassignment.domain.usecases.auth.LogoutAuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginAuthUseCases: LoginAuthUseCases,
    private val logoutAuthUseCases: LogoutAuthUseCases,
    private val getAuthUseCases: GetAuthUseCases
) : ViewModel() {

    var loginId by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    // Track the logout state
    var loggedOut by mutableStateOf(false)


    // Define authUiState as StateFlow
    val authUiState: StateFlow<AuthUiState> = getAuthUseCases.getAuthModel()
        .map<ResultData<AuthModel?>, AuthUiState> { result ->
            when (result) {
                is ResultData.SuccessData -> {
                    if (result.data != null) {
                        AuthUiState.Success(result.data)
                    } else {
                        AuthUiState.Error(Exception("No user found"))
                    }
                }
                is ResultData.ErrorData -> AuthUiState.Error(result.exception)
            }
        }
        .catch { throwable ->
            emit(AuthUiState.Error(throwable))
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AuthUiState.Loading)



    // Function to handle login logic
    fun login(onLoginSuccess: () -> Unit) {
        viewModelScope.launch {

            loginAuthUseCases.login(loginId, password).collect { authModel ->

                when (authModel) {
                    is ResultData.SuccessData -> {
                        onLoginSuccess()
                        errorMessage = null // Clear any previous error
                    }
                    is ResultData.ErrorData -> {
                        errorMessage = authModel.exception.message
                    }
                }
            }
        }
    }

    // Function to handle logout logic
    fun logout(onLogoutComplete: () -> Unit) {
        viewModelScope.launch {

            logoutAuthUseCases.logout().collect { isLoggedOut ->

                if (isLoggedOut) {
                    println("Logged out")

                    loggedOut = true // Set loggedOut to true on successful logout

                    loginId = ""
                    password = ""
                    resetCredentials() // Clear loginId and password
                    errorMessage = null // Clear any previous error

                    onLogoutComplete() // Callback to signal logout completion
                }
            }
        }
    }


    // New function to reset login credentials
    private fun resetCredentials() {
        loginId = ""
        password = ""
    }



    // Functions to update loginId and password fields
    fun onLoginIdChange(newLoginId: String) {
        loginId = newLoginId
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }
}