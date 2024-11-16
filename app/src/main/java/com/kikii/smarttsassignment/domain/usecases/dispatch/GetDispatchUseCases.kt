package com.kikii.smarttsassignment.domain.usecases.dispatch

import com.kikii.smarttsassignment.data.repository.default_repo.dispatch.DispatchRepository
import javax.inject.Inject


class GetDispatchUseCases @Inject constructor(
    private val dispatchRepository: DispatchRepository
){
    suspend fun getDispatchesByDate(date: String) = dispatchRepository.findDispatchesByDate(date)
}