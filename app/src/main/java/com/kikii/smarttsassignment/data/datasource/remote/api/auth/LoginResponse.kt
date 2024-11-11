package com.kikii.smarttsassignment.data.datasource.remote.api.auth

import com.google.gson.annotations.SerializedName

data class LoginResponse  (
    @SerializedName("userId")
    val userId: String,

    @SerializedName("loginId")
    val loginId: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("role")
    val role: String,

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