package com.kikii.smarttsassignment.data.datasource.remote.api.dispatch

import com.google.gson.annotations.SerializedName

data class DispatchResponse (
    @SerializedName("id")
    val id: Long,

    @SerializedName("driverId")
    val driverId: String,

    @SerializedName("driverName")
    val driverName: String,

    @SerializedName("routeId")
    val routeId: String,

    @SerializedName("routeName")
    val routeName: String,

    @SerializedName("busId")
    val busId: String,
)