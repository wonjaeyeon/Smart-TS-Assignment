package com.kikii.smarttsassignment.ui.feature.dispatch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kikii.smarttsassignment.data.common.ResultData
import com.kikii.smarttsassignment.domain.usecases.dispatch.FetchDispatchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DispatchViewModel @Inject constructor(
    private val fetchDispatchUseCases: FetchDispatchUseCases
) : ViewModel() {

    // Define a StateFlow for dispatch data
    private val _dispatchUiState: MutableStateFlow<DispatchUiState> = MutableStateFlow(DispatchUiState.Loading)
    val dispatchUiState: StateFlow<DispatchUiState> = _dispatchUiState

    // Fetch dispatch data for a given date
    fun fetchLatestDispatches(date: String) {
        viewModelScope.launch {
            _dispatchUiState.value = DispatchUiState.Loading // Emit Loading state initially
            fetchDispatchUseCases.fetchLatestDispatches(date)
                .collect { result ->
                    when (result) {
                        is ResultData.SuccessData -> {
                            result.data.let {
                                _dispatchUiState.value = DispatchUiState.Success(it)
                            }
                        }
                        is ResultData.ErrorData -> {
                            _dispatchUiState.value = DispatchUiState.Error(result.exception)
                        }
                    }
                }
        }
    }
}