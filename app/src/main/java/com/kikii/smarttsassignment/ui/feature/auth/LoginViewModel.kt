package com.kikii.smarttsassignment.ui.feature.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var loginId by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    // Function to handle login logic
    fun onLoginClick(onLoginSuccess: () -> Unit) {
        viewModelScope.launch {
            if (loginId == "djDEMO" && password == "DEMO") { // Sample login credentials
                onLoginSuccess()
                errorMessage = null // Clear any previous error
            } else {
                errorMessage = "로그인 실패: 아이디 또는 비밀번호가 잘못되었습니다."
            }
        }
    }

    // Functions to update loginId and password fields
    fun onLoginIdChange(newLoginId: String) {
        loginId = newLoginId
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }
}