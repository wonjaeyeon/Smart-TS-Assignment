package com.kikii.smarttsassignment.ui.feature.settings.sub

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

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