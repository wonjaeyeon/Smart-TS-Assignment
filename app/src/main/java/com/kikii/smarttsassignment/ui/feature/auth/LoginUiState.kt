package com.kikii.smarttsassignment.ui.feature.auth

sealed interface LoginUiState {
    object Loading : LoginUiState
    data class Success(val articles: List<String>) : LoginUiState
    data class Error(val throwable: Throwable) : LoginUiState
}