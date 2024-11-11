package com.kikii.smarttsassignment.ui.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun MainSettingsScreen(navController: NavHostController) {
// Add your settings UI here
    Column {
        // Button to navigate to Notification Settings
        Button(onClick = { navController.navigate(SettingRoute.NotificationSettings.toString()) }) {
            Text("Notification Settings")
        }
        // Button to navigate to Privacy Settings
        Button(onClick = { navController.navigate(SettingRoute.PrivacySettings.toString()) }) {
            Text("Privacy Settings")
        }
    }
}