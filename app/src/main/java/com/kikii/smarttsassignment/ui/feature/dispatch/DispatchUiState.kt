package com.kikii.smarttsassignment.ui.feature.dispatch

import com.kikii.smarttsassignment.data.model.DispatchModel

sealed class DispatchUiState {
    object Loading : DispatchUiState()
    data class Success(val data: List<DispatchModel?>) : DispatchUiState()
    data class Error(val exception: Throwable) : DispatchUiState()
}