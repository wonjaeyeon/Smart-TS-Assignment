package com.kikii.smarttsassignment.data.datasource.remote.api.dispatch

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

//data class DispatchRequest(
//    val date: String,
//)

data class DispatchRequest(
    val date: String
) {
    companion object {
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        // Function to create a DispatchRequest from a Date object
        fun fromDate(date: Date): DispatchRequest {
            return DispatchRequest(dateFormat.format(date))
        }
    }
}