package com.kikii.smarttsassignment.data.model

data class AuthModel (
    val userId: Long,

    val loginId: String,

    val name: String,

    val role: String,

    val userRoleId: Long,

    val userRoleName: String,

    val token: String,

    val companyId: Long,

    val companyGroupId: Long,

    val branchId: Long?,

    val branchName: String?,

    val position: String,

    val companyName: String,

    val deviceToken: String?,

    val agree: Int,

    val userWorkStandard: Int
)