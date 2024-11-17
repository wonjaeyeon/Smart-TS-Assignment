package com.kikii.smarttsassignment.ui.feature.settings.sub

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun NotificationSettingsScreen(navController: NavHostController) {
    Column {
        // Add your notification settings UI here
        Text("Notification Settings", fontSize = 36.sp, color = Color.White)
    }
}