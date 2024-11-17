package com.kikii.smarttsassignment.data.repository.default_repo.dispatch

import com.kikii.smarttsassignment.data.common.ResultData
import com.kikii.smarttsassignment.data.datasource.local.SmartTsAssignmentLocalDataSource
import com.kikii.smarttsassignment.data.datasource.remote.SmartTsAssignmentRemoteDataSource
import com.kikii.smarttsassignment.data.mapper.DispatchMapper
import com.kikii.smarttsassignment.data.model.DispatchModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
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
                val response = smartTsAssignmentRemoteDataSource.getDriverDispatch(jwt, date).first()
                if(response.isSuccessful){
                    val dispatch = response.body()
                    if(dispatch != null){
                        val listOfDispatchModel = dispatchMapper.fromDispatchResponseToDispatchModel(dispatch)
                        val listOfDispatchEntity = listOfDispatchModel.map {
                            dispatchMapper.fromDispatchModelToDispatchEntity(it, date)
                        }
                        smartTsAssignmentLocalDataSource.insertListOfDispatches(listOfDispatchEntity)
                        emit(ResultData.SuccessData(listOfDispatchModel))
                    } else {
                        emit(ResultData.ErrorData(Exception("No dispatch response found")))
                    }
                } else {
                    // Check for JWT Validation
                    if (response.body()?.status == 400) {
                        emit(ResultData.ErrorData(Exception("400: Invalid JWT")))
                    } else {
                        emit(ResultData.ErrorData(Exception("Error fetching dispatch: ${response.code()} ${response.message()}")))
                    }
                }

        }.catch {
            emit(ResultData.ErrorData(it.message?.let { message -> Exception(message) } ?: Exception("Unknown error")))
        }
    }

    override suspend fun findDispatchesByDate(date: String): Flow<ResultData<List<DispatchModel?>>> {
        return flow {
            val dispatches = smartTsAssignmentLocalDataSource.findDispatchesByDate(date)
            if(dispatches.isNotEmpty()){
                val listOfDispatchModel = dispatches.map {
                    dispatchMapper.fromDispatchEntityToDispatchModel(it)
                }
                emit(ResultData.SuccessData(listOfDispatchModel))
            } else {
                emit(ResultData.ErrorData(Exception("no dispatches(local stored) found")))
            }
        }.catch {
            emit(ResultData.ErrorData(it.message?.let { message -> Exception(message) } ?: Exception("Unknown error")))
        }
    }

    // TODO : 추후에 구현해놓기(한꺼번에 다 불러올 때 사용)
    override val localDispatchModels: Flow<ResultData<List<DispatchModel>>>
        get() = TODO()


}