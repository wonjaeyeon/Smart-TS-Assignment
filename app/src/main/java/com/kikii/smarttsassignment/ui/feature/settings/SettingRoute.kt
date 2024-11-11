package com.kikii.smarttsassignment.ui.feature.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable


// Define the routes for the settings
sealed interface SettingRoute {
    @Serializable
    object MainSettings : SettingRoute
    @Serializable
    object NotificationSettings : SettingRoute
    @Serializable
    object PrivacySettings : SettingRoute
}

@Composable
fun FirstSettingScreen() {
    SettingNavHost()
}

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

@Composable
fun NotificationSettingsScreen(navController: NavHostController) {
    Column {
        // Add your notification settings UI here
        Text("Notification Settings", fontSize = 36.sp, color = Color.White)
    }
}


// Privacy settings screen
@Composable
fun PrivacySettingsScreen(navController: NavController) {
    // UI for privacy settings
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Text(text = "Profile", fontSize = 36.sp, color = Color.White)

    }
}


// Navigation graph for the settings
@Composable
fun SettingNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = SettingRoute.MainSettings.toString()) {
        composable(SettingRoute.MainSettings.toString()) {
            MainSettingsScreen(navController)
        }
        composable(SettingRoute.NotificationSettings.toString()) {
            NotificationSettingsScreen(navController)
        }
        composable(SettingRoute.PrivacySettings.toString()) {
            PrivacySettingsScreen(navController)
        }
    }
}

