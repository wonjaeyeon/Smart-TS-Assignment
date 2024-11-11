package com.kikii.smarttsassignment.data.datasource.remote.api.dispatch

import com.google.gson.annotations.SerializedName

data class DispatchResponse (
    @SerializedName("id")
    val id: String,

    @SerializedName("driverId")
    val driverId: String,

    @SerializedName("driverName")
    val driverName: String,

    @SerializedName("routeName")
    val routeName: String,

    @SerializedName("token")
    val token: String,

    @SerializedName("companyId")
    val companyId: String,

    @SerializedName("position")
    val position: String,

    @SerializedName("companyName")
    val companyName: String,

    @SerializedName("deviceToken")
    val deviceToken: String,

    @SerializedName("agree")
    val agree: Boolean,
)