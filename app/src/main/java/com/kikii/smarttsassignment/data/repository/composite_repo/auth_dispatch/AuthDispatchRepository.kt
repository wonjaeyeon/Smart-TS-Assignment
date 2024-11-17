package com.kikii.smarttsassignment.data.repository.composite_repo.auth_dispatch

import com.kikii.smarttsassignment.data.common.ResultData
import com.kikii.smarttsassignment.data.model.DispatchModel
import kotlinx.coroutines.flow.Flow

interface AuthDispatchRepository {
    suspend fun fetchLatestDispatches(
        date : String
    ) : Flow<ResultData<List<DispatchModel?>>>

    val localDispatchModels : Flow<ResultData<List<DispatchModel>>>
}