package com.kikii.smarttsassignment.data.repository.default_repo.dispatch

import com.kikii.smarttsassignment.data.common.ResultData
import com.kikii.smarttsassignment.data.model.DispatchModel
import kotlinx.coroutines.flow.Flow

interface DispatchRepository {

    // local cache( = must)
    suspend fun fetchLatestDispatch(jwt : String, date : String) : Flow<ResultData<List<DispatchModel?>>>

    // call from local db
    val localDispatchModels : Flow<ResultData<List<DispatchModel>>>
}