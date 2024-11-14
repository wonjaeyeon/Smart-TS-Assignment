package com.kikii.smarttsassignment.data.repository.default_repo.dispatch

import com.kikii.smarttsassignment.data.common.ResultData
import com.kikii.smarttsassignment.data.model.DispatchModel
import kotlinx.coroutines.flow.Flow

interface DispatchRepository {

    // local cache( = must)
    suspend fun fetchLatestDispatch(jwt : String, date : String) : Flow<ResultData<List<DispatchModel?>>>

    // call from local db
    // TODO : 반드시 DATE 값이 필요하므로 함수를 하나 더 만들자.(이건 그냥 ALL 불러오는 용도)
    val localDispatchModels : Flow<ResultData<List<DispatchModel>>>
}