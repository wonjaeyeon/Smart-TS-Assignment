package com.kikii.smarttsassignment.data.mapper

import com.kikii.smarttsassignment.data.datasource.local.db.auth.AuthEntity
import com.kikii.smarttsassignment.data.datasource.remote.api.auth.AuthResponse
import com.kikii.smarttsassignment.data.model.AuthModel

object AuthMapper {

    // Convert LoginResponse to AuthEntity for local storage
    fun fromAuthResponseToAuthEntity(response: AuthResponse, password : String): AuthEntity {
        val obj = response.`object`
            ?: throw IllegalArgumentException("AuthResponse.object cannot be null")
        return AuthEntity(
            userId = obj.userId,
            loginId = obj.loginId,
            password = password,
            name = obj.name,
            role = obj.role,
            userRoleId = obj.userRoleId,
            userRoleName = obj.userRoleName,
            token = obj.token,
            companyId = obj.companyId,
            companyGroupId = obj.companyGroupId,
            branchId = obj.branchId,
            branchName = obj.branchName,
            position = obj.position,
            companyName = obj.companyName,
            deviceToken = obj.deviceToken,
            agree = obj.agree,
            userWorkStandard = obj.userWorkStandard
        )
    }

    // Convert AuthEntity to AuthModel for application use
    fun fromAuthEntityToAuthModel(entity: AuthEntity): AuthModel {
        return AuthModel(
            userId = entity.userId,
            loginId = entity.loginId,
            name = entity.name,
            role = entity.role,
            userRoleId = entity.userRoleId,
            userRoleName = entity.userRoleName,
            token = entity.token,
            companyId = entity.companyId,
            companyGroupId = entity.companyGroupId,
            branchId = entity.branchId,
            branchName = entity.branchName,
            position = entity.position,
            companyName = entity.companyName,
            deviceToken = entity.deviceToken,
            agree = entity.agree,
            userWorkStandard = entity.userWorkStandard
        )
    }

    // Convert LoginResponse directly to AuthModel
    fun fromLoginResponseToAuthModel(response: AuthResponse): AuthModel {
        val obj = response.`object`
            ?: throw IllegalArgumentException("AuthResponse.object cannot be null")
        return AuthModel(
            userId = obj.userId,
            loginId = obj.loginId,
            name = obj.name,
            role = obj.role,
            userRoleId = obj.userRoleId,
            userRoleName = obj.userRoleName,
            token = obj.token,
            companyId = obj.companyId,
            companyGroupId = obj.companyGroupId,
            branchId = obj.branchId,
            branchName = obj.branchName,
            position = obj.position,
            companyName = obj.companyName,
            deviceToken = obj.deviceToken,
            agree = obj.agree,
            userWorkStandard = obj.userWorkStandard
        )
    }

    // Convert AuthModel to AuthEntity for local storage
    fun fromAuthModelToAuthEntity(model: AuthModel, password: String): AuthEntity {
        return AuthEntity(
            userId = model.userId,
            loginId = model.loginId,
            password = password,
            name = model.name,
            role = model.role,
            userRoleId = model.userRoleId,
            userRoleName = model.userRoleName,
            token = model.token,
            companyId = model.companyId,
            companyGroupId = model.companyGroupId,
            branchId = model.branchId,
            branchName = model.branchName,
            position = model.position,
            companyName = model.companyName,
            deviceToken = model.deviceToken,
            agree = model.agree,
            userWorkStandard = model.userWorkStandard
        )
    }
}