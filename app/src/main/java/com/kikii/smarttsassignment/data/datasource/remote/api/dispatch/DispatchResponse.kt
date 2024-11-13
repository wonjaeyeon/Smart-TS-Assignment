package com.kikii.smarttsassignment.data.datasource.remote.api.dispatch

import com.google.gson.annotations.SerializedName

//data class DispatchResponse (
//    @SerializedName("id")
//    val id: Long,
//
//    @SerializedName("driverId")
//    val driverId: String,
//
//    @SerializedName("driverName")
//    val driverName: String,
//
//    @SerializedName("routeId")
//    val routeId: String,
//
//    @SerializedName("routeName")
//    val routeName: String,
//
//    @SerializedName("busId")
//    val busId: String,
//)

data class DispatchResponse(
    @SerializedName("status")
    val status: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("object")
    val `object`: List<Object> // List of DispatchObject to match array structure in JSON
)

data class Object(
    @SerializedName("id")
    val id: Long?,

    @SerializedName("startOrder")
    val startOrder: Long,

    @SerializedName("routeName")
    val routeName: String,

    @SerializedName("routeId")
    val routeId: Long,

    @SerializedName("driverName")
    val driverName: String,

    @SerializedName("driverId")
    val driverId: Long,

    @SerializedName("busId")
    val busId: Long,

    @SerializedName("busNumber")
    val busNumber: String,

    @SerializedName("busType")
    val busType: String,

    @SerializedName("startTime")
    val startTime: String,

    @SerializedName("endTime")
    val endTime: String?,

    @SerializedName("actualEndTime")
    val actualEndTime: String?,

    @SerializedName("breakTime")
    val breakTime: String?,

    @SerializedName("unixStartTime")
    val unixStartTime: Long?,

    @SerializedName("unixEndTime")
    val unixEndTime: Long?,

    @SerializedName("busRound")
    val busRound: Int,

    @SerializedName("remainEnd")
    val remainEnd: String?,

    @SerializedName("shift")
    val shift: String?
)