package com.kikii.smarttsassignment.data.datasource.remote.api.route

import com.google.gson.annotations.SerializedName

data class RouteResponse (
    @SerializedName("status")
    val status: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("object")
    val `object`: Object?
)

data class Object(
    @SerializedName("id")
    val id: Long,

    @SerializedName("shift")
    val shift: String,

    @SerializedName("driverId")
    val driverId: Long,

    @SerializedName("driverName")
    val driverName: String,

    @SerializedName("routeId")
    val routeId: Long,

    @SerializedName("routeName")
    val routeName: String,

    @SerializedName("routeType")
    val routeType: String,

    @SerializedName("busId")
    val busId: Long,

    @SerializedName("busNumber")
    val busNumber: String,

    @SerializedName("busType")
    val busType: String,

    @SerializedName("branchId")
    val branchId: Long,

    @SerializedName("branchName")
    val branchName: String,

    @SerializedName("updatedManagerName")
    val updatedManagerName: String,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String,

    @SerializedName("reason")
    val reason: String?
)