package com.kikii.smarttsassignment.data.datasource.remote.api.auth

import com.google.gson.annotations.SerializedName

// check
data class AuthResponse(
    @SerializedName("status")
    val status: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("object")
    val `object`: Object?
)

data class Object(
    @SerializedName("userId")
    val userId: Long,

    @SerializedName("loginId")
    val loginId: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("role")
    val role: String,

    @SerializedName("userRoleId")
    val userRoleId: Long,

    @SerializedName("userRoleName")
    val userRoleName: String,

    @SerializedName("token")
    val token: String,

    @SerializedName("companyId")
    val companyId: Long,

    @SerializedName("companyGroupId")
    val companyGroupId: Long,

    @SerializedName("branchId")
    val branchId: Long?,

    @SerializedName("branchName")
    val branchName: String?,

    @SerializedName("position")
    val position: String,

    @SerializedName("companyName")
    val companyName: String,

    @SerializedName("deviceToken")
    val deviceToken: String?,

    @SerializedName("agree")
    val agree: Int,

    @SerializedName("userWorkStandard")
    val userWorkStandard: Int
)