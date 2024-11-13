package com.kikii.smarttsassignment.data.repository.default_repo.dispatch

import com.kikii.smarttsassignment.data.common.ResultData
import com.kikii.smarttsassignment.data.datasource.local.SmartTsAssignmentLocalDataSource
import com.kikii.smarttsassignment.data.datasource.remote.SmartTsAssignmentRemoteDataSource
import com.kikii.smarttsassignment.data.mapper.DispatchMapper
import com.kikii.smarttsassignment.data.model.DispatchModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultDispatchRepository @Inject constructor(
    private val smartTsAssignmentLocalDataSource: SmartTsAssignmentLocalDataSource,
    private val smartTsAssignmentRemoteDataSource: SmartTsAssignmentRemoteDataSource,
    private val dispatchMapper: DispatchMapper
) : DispatchRepository {

    override suspend fun fetchLatestDispatch(jwt: String, date : String): Flow<ResultData<List<DispatchModel?>>> {
        return flow {
            try{
                val response = smartTsAssignmentRemoteDataSource.getDriverDispatch(jwt, date).first()
                if(response.isSuccessful){
                    val dispatch = response.body()
                    if(dispatch != null){
                        val listOfDispatchModel = dispatchMapper.fromDispatchResponseToDispatchModel(dispatch)
                        val listOfDispatchEntity = listOfDispatchModel.map {
                            dispatchMapper.fromDispatchModelToDispatchEntity(it)
                        }
                        smartTsAssignmentLocalDataSource.insertListOfDispatches(listOfDispatchEntity)
                        emit(ResultData.SuccessData(listOfDispatchModel))
                    } else {
                        emit(ResultData.ErrorData(Exception("No dispatch found")))
                    }
                } else {
                    emit(ResultData.ErrorData(Exception("Error fetching dispatch")))
                }
            } catch (e: Exception){
                emit(ResultData.ErrorData(e))
            }
        }
    }

    override val localDispatchModels: Flow<ResultData<List<DispatchModel>>>
        get() = TODO()


}