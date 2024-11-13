package com.kikii.smarttsassignment.ui.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kikii.smarttsassignment.ui.feature.settings.sub.AccountSettingsScreen
import com.kikii.smarttsassignment.ui.feature.settings.sub.NotificationSettingsScreen
import com.kikii.smarttsassignment.ui.feature.settings.sub.PrivacySettingsScreen
import kotlinx.serialization.Serializable


// Define the routes for the settings
sealed interface SettingRoute {
    @Serializable
    object MainSettings : SettingRoute
    @Serializable
    object NotificationSettings : SettingRoute
    @Serializable
    object PrivacySettings : SettingRoute
    @Serializable
    object AccountSettings : SettingRoute
}

@Composable
fun SettingScreen() {
    SettingNavHost()
}

// Navigation graph for the settings
@Composable
fun SettingNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = SettingRoute.MainSettings.toString()) {
        composable(SettingRoute.MainSettings.toString()) {
            MainSettingsScreen(
                navController = navController)
        }
        composable(SettingRoute.NotificationSettings.toString()) {
            NotificationSettingsScreen(navController)
        }
        composable(SettingRoute.PrivacySettings.toString()) {
            PrivacySettingsScreen(navController)
        }
        composable(SettingRoute.AccountSettings.toString()) {
            AccountSettingsScreen(navController = navController)
        }
    }
}

